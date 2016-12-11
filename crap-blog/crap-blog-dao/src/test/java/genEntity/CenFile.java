package genEntity;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CenFile {
  private static String templePath = "test.java.genTemple";
  public static String processIDao(String tableName) throws IOException {
		 StringBuilder sb = readTemple("IDao");
		 return sb.toString().replaceAll("_DEMAIN_", initcap(tableName));
  }
  
  public static String processDaoImp(String tableName) throws IOException {
		 StringBuilder sb = readTemple("DaoImp");
		 return sb.toString().replaceAll("_DEMAIN_", initcap(tableName));
  }

  public static String processDaoMapper(String tableName) throws IOException {
		 StringBuilder sb = readTemple("DaoMapper");
		 return sb.toString().replaceAll("_DEMAIN_", initcap(tableName));
 }
  
	public static String processMapper(String tableName) throws FileNotFoundException, IOException {
		 StringBuilder sb = readTemple("Mapper");
		
		 // 列
		 StringBuilder colnames = new StringBuilder();
		 for (int i = 0; i < GenEntityUtil.colnames.length; i++) {
			 colnames.append(" <result column=\""+GenEntityUtil.tablecolnames[i]+"\" jdbcType=\""+sqlType2MybatisType(GenEntityUtil.colTypes[i])+"\" property=\""+GenEntityUtil.colnames[i]+"\" />\r\n");
		 }
		
		 // 插入
		 StringBuilder colnamesStr = new StringBuilder();
		 StringBuilder fieldsStr = new StringBuilder();
		 for (int i = 0; i < GenEntityUtil.tablecolnames.length; i++) {
			 if(GenEntityUtil.tablecolnames[i].equals("id"))
				 continue;
			 if( !GenEntityUtil.tablecolnames[i].equals("createTime")){
				 colnamesStr.append(GenEntityUtil.tablecolnames[i]+",");
				 fieldsStr.append("#{"+GenEntityUtil.colnames[i]+"},");
			 }else{
				 colnamesStr.append(GenEntityUtil.tablecolnames[i]+",");
				 fieldsStr.append("now(),");
			 }
		 }
		 if( colnamesStr.length() > 0)
			 colnamesStr.delete( colnamesStr.length()-1, colnamesStr.length());
		 if( fieldsStr.length() > 0)
			 fieldsStr.delete( fieldsStr.length()-1, fieldsStr.length());
		 
		 StringBuilder updateStr = new StringBuilder();
		 // 更新
		 for (int i = 0; i < GenEntityUtil.tablecolnames.length; i++) {
			 if(GenEntityUtil.tablecolnames[i].equals("id"))
				 continue;
			 updateStr.append("\t<if test=\"" + GenEntityUtil.colnames[i] + " != null\">\r\n\t\t"+ GenEntityUtil.tablecolnames[i] + "=#{"+  GenEntityUtil.colnames[i] +"},\r\n\t</if>\r\n");
		 }
//		 if( updateStr.length() > 0)
//			 updateStr.delete( updateStr.length()-11, updateStr.length()-10);
//				 
		 return String.format(sb.toString(),  colnames.toString(), colnamesStr.toString(), colnamesStr.toString(), fieldsStr.toString(), updateStr.toString()).
				 replaceAll("_DEMAIN_", initcap(tableName)).replaceAll("_TABLE_", tableName);
  }

  
  public static String processIService(String tableName) throws FileNotFoundException, IOException {
	  StringBuilder sb = readTemple("IService");
	  return sb.toString().replaceAll("_DEMAIN_", initcap(tableName));
  }
  
  public static String processServiceImp(String tableName) throws FileNotFoundException, IOException {
	  StringBuilder sb = readTemple("ServiceImp");
	  return sb.toString().replaceAll("_DEMAIN_", initcap(tableName));
}
  
  public static String processDomain(String tableName) throws FileNotFoundException, IOException {
	    StringBuilder sb = readTemple("Domain");
	    StringBuilder temple = new StringBuilder();
		for (int i = 0; i < GenEntityUtil.colnames.length; i++) {
			temple.append("\tprivate " + sqlType2JavaType(GenEntityUtil.colTypes[i]) + " " + GenEntityUtil.colnames[i] + ";\r\n");
		}
		String fields = temple.toString();
		temple = new StringBuilder();
		
		processAllMethod(temple);
		return String.format(sb.toString(), initcap(tableName), fields, temple.toString());
  }
  
  /**
	 * 生成所有的方法
	 * 
	 * @param sb
	 */
	public static void processAllMethod(StringBuilder sb) {
		for (int i = 0; i < GenEntityUtil.colnames.length; i++) {
			sb.append("\tpublic void set" + initcap(GenEntityUtil.colnames[i]) + "(" + sqlType2JavaType(GenEntityUtil.colTypes[i]) + " "
					+ GenEntityUtil.colnames[i] + "){\r\n");
			sb.append("\t\tthis." + GenEntityUtil.colnames[i] + "=" + GenEntityUtil.colnames[i] + ";\r\n");
			sb.append("\t}\r\n");

			sb.append("\tpublic " + sqlType2JavaType(GenEntityUtil.colTypes[i]) + " get" + initcap(GenEntityUtil.colnames[i]) + "(){\r\n");
			sb.append("\t\treturn " + GenEntityUtil.colnames[i] + ";\r\n");
			sb.append("\t}\r\n\r\n");
		}
	}
	
	
  
  
  private static StringBuilder readTemple(String fileName) throws FileNotFoundException, IOException {
	String daoStr = System.getProperty("user.dir") + "/src/" + templePath.replaceAll("\\.", "/");
	 FileReader fr = new FileReader(daoStr+"/"+fileName);
	 BufferedReader br = new BufferedReader(fr);
	 String s;
	 StringBuilder sb = new StringBuilder();
	 while ((s = br.readLine()) != null) {
		 sb.append(s+"\r\n");
	 }
	 fr.close();
	 br.close();
	return sb;
  }
  
	
	private static String sqlType2MybatisType(String sqlType) {
		if (sqlType.equalsIgnoreCase("bit")) {
			return "BIT";
		} else if (sqlType.equalsIgnoreCase("int") || sqlType.equalsIgnoreCase("integer")) {
			return "INTEGER";
		} else if (sqlType.equalsIgnoreCase("bigint")) {
			return "BIGINT";
		} else if (sqlType.equalsIgnoreCase("float")) {
			return "FLOAT";
		} else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
				|| sqlType.equalsIgnoreCase("real")) {
			return "DECIMAL";
		}else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
				|| sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")) {
			return "VARCHAR";
		} else if (sqlType.equalsIgnoreCase("datetime")) {
			return "DATE";
		}else if (sqlType.equalsIgnoreCase("text")) {
			return "TEXT";
		}else if (sqlType.equalsIgnoreCase("tinyint")) {
			return "TINYINT";
		}else if (sqlType.equalsIgnoreCase("TIMESTAMP")){
			return "VARCHAR";
		}
		return "";
	}
	public static String sqlType2JavaType(String sqlType) {
		if (sqlType.equalsIgnoreCase("bit")) {
			return "Boolean";
		} else if (sqlType.equalsIgnoreCase("tinyint")) {
			return "Byte";
		} else if (sqlType.equalsIgnoreCase("smallint")) {
			return "Short";
		} else if (sqlType.equalsIgnoreCase("int") || sqlType.equalsIgnoreCase("integer")) {
			return "Integer";
		} else if (sqlType.equalsIgnoreCase("bigint")) {
			return "Long";
		} else if (sqlType.equalsIgnoreCase("float")) {
			return "Float";
		} else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
				|| sqlType.equalsIgnoreCase("real")) {
			return "Double";
		} else if (sqlType.equalsIgnoreCase("money") || sqlType.equalsIgnoreCase("smallmoney")) {
			return "Double";
		} else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
				|| sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")) {
			return "String";
		} else if (sqlType.equalsIgnoreCase("datetime")) {
			return "String";
		}else if (sqlType.equalsIgnoreCase("TIMESTAMP")){
			return "String";
		}

		else if (sqlType.equalsIgnoreCase("image")) {
			return "Blob";
		} else if (sqlType.equalsIgnoreCase("text")) {
			return "Clob";
		}
		return null;
	}
	/**
	 * 把输入字符串的首字母改成大写
	 * 
	 * @param str
	 * @return
	 */
	public static String initcap(String str) {
		str=str.replace("customer_", "");
		str = getCamelStr(str);
		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}
		return new String(ch);
	}
	
	// 例：user_name --> userName
	public static String getCamelStr(String s) {
			s = s.toLowerCase();
			while (s.indexOf("_") > 0) {
				int index = s.indexOf("_");
				s = s.substring(0, index) + s.substring(index + 1, index + 2).toUpperCase() + s.substring(index + 2);
			}
			return s;
	}
	
	public static void writeStringToFile(File file, String content) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
		writer.write(content);
		writer.close();
		
	}
}
