package studentResultCardProgram;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentResultController {
	public static final Scanner scan = new Scanner(System.in);

	// 데이터베이스 연결해서 학생번호 중복값 비교
	public static List<StudentResult> studentResultStudentNumNotOverlapTBL(String studentNum) {
		List<StudentResult> list = new ArrayList<StudentResult>();
		// 데이터베이스에 연결
		Connection con = DBUtility.getConnection();
		String searchQuery = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		// 검색할 내용 입력받기(검색할 조건을 개개인이 부여하기.)
		try {
			searchQuery = "select * from studentresulttbl where studentnum like ?";

			preparedStatement = con.prepareStatement(searchQuery);
			studentNum = "%" + studentNum + "%";
			preparedStatement.setString(1, studentNum);

			resultSet = preparedStatement.executeQuery();
			if (!resultSet.isBeforeFirst()) {
				return list;
			}

			while (resultSet.next()) {
				String studentNo = resultSet.getString(1);
				String studentName = resultSet.getString(2);
				int korScore = resultSet.getInt(3);
				int mathScore = resultSet.getInt(4);
				int engScore = resultSet.getInt(5);
				int total = resultSet.getInt(6);
				double avg = resultSet.getDouble(7);
				String grade = resultSet.getString(8);
				StudentResult studentResult = new StudentResult(studentNo, studentName, korScore, mathScore, engScore,
						total, avg, grade);
				list.add(studentResult);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
			}
		}
		return list;
	}

	// 데이터 베이스 연결해서 학번,이름,점수 입력 시 procedure를 통해 합산점수,평균,등급 알려주는 procedure
	public static int studentResultInputTBL(StudentResult studentResult) {
		// 데이타베이스에 연결
		Connection con = DBUtility.getConnection();
		String insertQuery = "call procedure_calculate_studentresulttbl(?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		int count = 0;
		try {
			preparedStatement = con.prepareStatement(insertQuery);

			preparedStatement.setString(1, studentResult.getStudentNum());
			preparedStatement.setString(2, studentResult.getStudnetName());
			preparedStatement.setInt(3, studentResult.getKorScore());
			preparedStatement.setInt(4, studentResult.getMathScore());
			preparedStatement.setInt(5, studentResult.getEngScore());
			count = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
				if (con != null && !con.isClosed()) {
					con.close();
				}

			} catch (SQLException e) {

			}
		}
		return count;
	}

	// 데이터베이스에 연결해서 studentresulttbl에 입력된 정보 가져오기
	public static List<StudentResult> studentResultInquiryTBL() {
		List<StudentResult> list = new ArrayList<StudentResult>();
		// 데이타베이스에 연결
		Connection con = DBUtility.getConnection();
		String selectQuery = "select * from studentresulttbl";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = con.prepareStatement(selectQuery);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String studentNo = resultSet.getString(1);
				String studentName = resultSet.getString(2);
				int korScore = resultSet.getInt(3);
				int mathScore = resultSet.getInt(4);
				int engScore = resultSet.getInt(5);
				int total = resultSet.getInt(6);
				double avg = resultSet.getDouble(7);
				String grade = resultSet.getString(8);
				StudentResult studentResult = new StudentResult(studentNo, studentName, korScore, mathScore, engScore,
						total, avg, grade);
				list.add(studentResult);
				System.out.println(studentResult.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
				if (con != null && !con.isClosed()) {
					con.close();
				}

			} catch (SQLException e) {
			}
		}
		return list;
	}

	// 데이터베이스에 연결해, studentresulttbl에 입력된 데이터 삭제
	public static int studentResultDeleteTBL(String studentNum) {
		// 데이타베이스에 연결
		Connection con = DBUtility.getConnection();
		String deleteQuery = "DELETE from studentresulttbl where studentnum like ?";
		PreparedStatement preparedStatement = null;
		int count = 0;

		try {
			preparedStatement = con.prepareStatement(deleteQuery);
			String strStudentNum = "%" + studentNum + "%";
			preparedStatement.setString(1, strStudentNum);
			count = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
				if (con != null && !con.isClosed()) {
					con.close();
				}

			} catch (SQLException e) {
			}
		}
		return count;
	}

	// 데이터베이스에 연결해, 삭제된 학생 정보를 가져오는 것
	public static List<StudentResult> studentResultWastebasketTBL() {
		List<StudentResult> list = new ArrayList<StudentResult>();
		// 데이타베이스에 연결
		Connection con = DBUtility.getConnection();
		String selectQuery = "select * from onDeletestudentresulttbl;";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = con.prepareStatement(selectQuery);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String studentNo = resultSet.getString(1);
				String studentName = resultSet.getString(2);
				int korScore = resultSet.getInt(3);
				int mathScore = resultSet.getInt(4);
				int engScore = resultSet.getInt(5);
				int total = resultSet.getInt(6);
				double avg = resultSet.getDouble(7);
				String grade = resultSet.getString(8);
				StudentResult studentResult = new StudentResult(studentNo, studentName, korScore, mathScore, engScore,
						total, avg, grade);
				list.add(studentResult);
				System.out.println(studentResult.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
				if (con != null && !con.isClosed()) {
					con.close();
				}

			} catch (SQLException e) {
			}
		}
		return list;
	}

	// 데이터베이스에 연결해 수정된 값을 전달해주는 function
	public static int studentResultUpdateTBL(String studentNum, int korScore, int mathScore, int engScore) {
		Connection con = DBUtility.getConnection();
		String updateQuery = "select function_update_studentresulttbl(?,?,?,?)";
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int count = 0;
		try {
			preparedStatement = con.prepareStatement(updateQuery);
			preparedStatement.setString(1, studentNum);
			preparedStatement.setInt(2, korScore);
			preparedStatement.setInt(3, mathScore);
			preparedStatement.setInt(4, engScore);
			rs = preparedStatement.executeQuery();
			if (rs.next() == false) {
				count = 0;
			} else {
				count = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
			}
		}
		return count;
	}

	// 데이터베이스에 연결해, studentNum으로 검색된 정보 출력
	public static void studentNUM_SEARCH(String studentNum) {
		Connection con = DBUtility.getConnection();
		ResultSet rs = null;
		String strQuery = "select * from studentresulttbl where studentnum = ?";
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = con.prepareStatement(strQuery);
			preparedStatement.setString(1, studentNum);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String studentNo = rs.getString(1);
				String studentName = rs.getString(2);
				int korScore = rs.getInt(3);
				int mathScore = rs.getInt(4);
				int engScore = rs.getInt(5);
				int total = rs.getInt(6);
				double avg = rs.getDouble(7);
				String grade = rs.getString(8);
				StudentResult sr = new StudentResult(studentNo, studentName, korScore, mathScore, engScore, total, avg,
						grade);
				System.out.println(sr.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
			}
		}

	}

	// 데이터베이스에 연결해, studentNum으로 검색된 정보 출력
	public static void studentNAME_SEARCH(String studentName) {
		Connection con = DBUtility.getConnection();
		ResultSet rs = null;
		String strQuery = "select * from studentresulttbl where studentname = ?";
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = con.prepareStatement(strQuery);
			preparedStatement.setString(1, studentName);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String studentNo = rs.getString(1);
				String name = rs.getString(2);
				int korScore = rs.getInt(3);
				int mathScore = rs.getInt(4);
				int engScore = rs.getInt(5);
				int total = rs.getInt(6);
				double avg = rs.getDouble(7);
				String grade = rs.getString(8);
				StudentResult student = new StudentResult(studentNo, name, korScore, mathScore, engScore, total, avg,
						grade);
				System.out.println(student.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
			}
		}
	}

	// 데이터베이스에 연결해 입력된 값을 학번 오름차순으로 출력
	public static void studentNum_ASC() {
		Connection con = DBUtility.getConnection();
		ResultSet rs = null;
		String strQuery = "select * from studentresulttbl order by studentNum asc";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = con.prepareStatement(strQuery);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String studentNumber = rs.getString(1);
				String name = rs.getString(2);
				int korScore = rs.getInt(3);
				int mathScore = rs.getInt(4);
				int engScore = rs.getInt(5);
				int total = rs.getInt(6);
				double avg = rs.getDouble(7);
				String grade = rs.getString(8);
				StudentResult sr = new StudentResult(studentNumber, name, korScore, mathScore, engScore, total, avg,
						grade);
				System.out.println(sr.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
			}
		}
	}

	// 데이터베이스에 연결해, 학생평균점수를 내림차순으로 출력
	public static void studentAvg_DESC() {
		Connection con = DBUtility.getConnection();
		ResultSet rs = null;
		String strQuery = "select * from studentresulttbl order by avg desc";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = con.prepareStatement(strQuery);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String studentNumber = rs.getString(1);
				String name = rs.getString(2);
				int korScore = rs.getInt(3);
				int mathScore = rs.getInt(4);
				int engScore = rs.getInt(5);
				int total = rs.getInt(6);
				double avg = rs.getDouble(7);
				String grade = rs.getString(8);
				StudentResult studentResult = new StudentResult(studentNumber, name, korScore, mathScore, engScore,
						total, avg, grade);
				System.out.println(studentResult.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
			}
		}
	}

	// 데이터 베이스에 연결해, 학생이름검색으로 정보 조회 시, 존재하지않는 이름 허용 X
	public static List<StudentResult> studentResultStudentNameNotOverlapTBL(String studentName) {
		List<StudentResult> list = new ArrayList<StudentResult>();
		// 데이터베이스에 연결
		Connection con = DBUtility.getConnection();
		String searchQuery = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		// 검색할 내용 입력받기(검색할 조건을 개개인이 부여하기.)
		try {
			searchQuery = "select * from studentresulttbl where studentname like ?";

			preparedStatement = con.prepareStatement(searchQuery);
			studentName = "%" + studentName + "%";
			preparedStatement.setString(1, studentName);

			resultSet = preparedStatement.executeQuery();
			if (!resultSet.isBeforeFirst()) {
				return list;
			}

			while (resultSet.next()) {
				String studentNo = resultSet.getString(1);
				String name = resultSet.getString(2);
				int korScore = resultSet.getInt(3);
				int mathScore = resultSet.getInt(4);
				int engScore = resultSet.getInt(5);
				int total = resultSet.getInt(6);
				double avg = resultSet.getDouble(7);
				String grade = resultSet.getString(8);
				StudentResult studentResult = new StudentResult(studentNo, name, korScore, mathScore, engScore, total,
						avg, grade);
				list.add(studentResult);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
			}
		}
		return list;
	}
}