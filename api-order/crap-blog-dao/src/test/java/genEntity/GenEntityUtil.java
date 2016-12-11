package genEntity;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class GenEntityUtil {

	public static String[] colnames; // 列名数组
	public static String[] tablecolnames; // 列名类型数组
	public static String[] colTypes; // 列名类型数组
	//public static int[] colSizes; // 列名大小数组

	public GenEntityUtil(String tableName) {
		Connection conn = openConnection(); // 得到数据库连接
		PreparedStatement pstmt = null;
		String strsql = "select * from " + tableName;
		try {
			pstmt = conn.prepareStatement(strsql);
			ResultSetMetaData rsmd = pstmt.getMetaData();
			int size = rsmd.getColumnCount(); // 共有多少列
			colnames = new String[size];
			colTypes = new String[size];
			tablecolnames = new String[size];
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				tablecolnames[i] = rsmd.getColumnName(i + 1);
				colnames[i] = CenFile.getCamelStr(rsmd.getColumnName(i + 1));
				colTypes[i] = rsmd.getColumnTypeName(i + 1);
			}
			try {
				String basePath = System.getProperty("user.dir").substring(0, System.getProperty("user.dir").lastIndexOf("/"));
				
				// IDao
				String srcPath = basePath+"/crap-blog-dao/src/main/java/cn/crap/blog/dao";
				File file = new File(srcPath);
				if (!file.exists()) {
					file.mkdirs();
				}
				String fileName = srcPath + "/I" + CenFile.initcap(tableName) + "Dao.java";
				file = new File(fileName);
				if (file.exists()) {
					file.delete();
				}
				CenFile.writeStringToFile(new File(fileName), CenFile.processIDao(tableName));
				
				// DaoImp
				srcPath = basePath+"/crap-blog-dao/src/main/java/cn/crap/blog/dao/imp";
				file = new File(srcPath);
				if (!file.exists()) {
					file.mkdirs();
				}
			    fileName = srcPath + "/" + CenFile.initcap(tableName) + "Dao.java";
				file = new File(fileName);
				if (file.exists()) {
					file.delete();
				}
				CenFile.writeStringToFile(new File(fileName), CenFile.processDaoImp(tableName));
				
				// DaoMapper
				srcPath = basePath+"/crap-blog-dao/src/main/java/cn/crap/blog/dao/mapper";
				file = new File(srcPath);
				if (!file.exists()) {
					file.mkdirs();
				}
				fileName = srcPath + "/" + CenFile.initcap(tableName) + "Mapper.java";
				file = new File(fileName);
				if (file.exists()) {
					file.delete();
				}
				CenFile.writeStringToFile(new File(fileName), CenFile.processDaoMapper(tableName));
				

				// Mapper
				srcPath = basePath+"/crap-blog-dao/src/main/resources/mapping";
				file = new File(srcPath);
				if (!file.exists()) {
					file.mkdirs();
				}
				fileName = srcPath + "/" + CenFile.initcap(tableName) + "Mapper.xml";
				file = new File(fileName);
				if (file.exists()) {
					file.delete();
				}
				CenFile.writeStringToFile(new File(fileName), CenFile.processMapper(tableName));
				
				// 生成IService
				srcPath = basePath+"/crap-blog-service/src/main/java/cn/crap/blog/service";
				file = new File(srcPath);
				if (!file.exists()) {
					file.mkdirs();
				}
				fileName = srcPath + "/I" + CenFile.initcap(tableName) + "Service.java";
				file = new File(fileName);
				if (file.exists()) {
					file.delete();
				}
				CenFile.writeStringToFile(new File(fileName), CenFile.processIService(tableName));

				// 生成IService
				srcPath = basePath+"/crap-blog-service/src/main/java/cn/crap/blog/service/imp";
				file = new File(srcPath);
				if (!file.exists()) {
					file.mkdirs();
				}
				fileName = srcPath + "/" + CenFile.initcap(tableName) + "Service.java";
				file = new File(fileName);
				if (file.exists()) {
					file.delete();
				}
				CenFile.writeStringToFile(new File(fileName), CenFile.processServiceImp(tableName));
				
				// 生成IService
				srcPath = basePath+"/crap-blog-domain/src/main/java/cn/crap/blog/domain/dao";
				file = new File(srcPath);
				if (!file.exists()) {
					file.mkdirs();
				}
				fileName = srcPath + "/" + CenFile.initcap(tableName) + ".java";
				file = new File(fileName);
				if (file.exists()) {
					file.delete();
				}
				CenFile.writeStringToFile(new File(fileName), CenFile.processDomain(tableName));


			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDatabase(conn, pstmt);
		}
	}


	public static Connection openConnection() {
		try {
			// 加载MySql的驱动类
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("找不到驱动程序类 ，加载驱动失败！");
			e.printStackTrace();
		}
		String url = "jdbc:mysql://localhost:3306/blogdev?useUnicode=true&characterEncoding=utf-8";
		String username = "blogdev";
		String password = "blogdev";
		try {
			Connection con = DriverManager.getConnection(url, username, password);
			return con;
		} catch (SQLException se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}
		return null;

	}
	private void closeDatabase(Connection conn, PreparedStatement pstmt) {
		if( conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if( pstmt != null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public static void main(String[] args) {
		String tableName = "setting";
		new GenEntityUtil(tableName);
		
	}

}