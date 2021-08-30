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

	// �����ͺ��̽� �����ؼ� �л���ȣ �ߺ��� ��
	public static List<StudentResult> studentResultStudentNumNotOverlapTBL(String studentNum) {
		List<StudentResult> list = new ArrayList<StudentResult>();
		// �����ͺ��̽��� ����
		Connection con = DBUtility.getConnection();
		String searchQuery = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		// �˻��� ���� �Է¹ޱ�(�˻��� ������ �������� �ο��ϱ�.)
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

	// ������ ���̽� �����ؼ� �й�,�̸�,���� �Է� �� procedure�� ���� �ջ�����,���,��� �˷��ִ� procedure
	public static int studentResultInputTBL(StudentResult studentResult) {
		// ����Ÿ���̽��� ����
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

	// �����ͺ��̽��� �����ؼ� studentresulttbl�� �Էµ� ���� ��������
	public static List<StudentResult> studentResultInquiryTBL() {
		List<StudentResult> list = new ArrayList<StudentResult>();
		// ����Ÿ���̽��� ����
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

	// �����ͺ��̽��� ������, studentresulttbl�� �Էµ� ������ ����
	public static int studentResultDeleteTBL(String studentNum) {
		// ����Ÿ���̽��� ����
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

	// �����ͺ��̽��� ������, ������ �л� ������ �������� ��
	public static List<StudentResult> studentResultWastebasketTBL() {
		List<StudentResult> list = new ArrayList<StudentResult>();
		// ����Ÿ���̽��� ����
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

	// �����ͺ��̽��� ������ ������ ���� �������ִ� function
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

	// �����ͺ��̽��� ������, studentNum���� �˻��� ���� ���
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

	// �����ͺ��̽��� ������, studentNum���� �˻��� ���� ���
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

	// �����ͺ��̽��� ������ �Էµ� ���� �й� ������������ ���
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

	// �����ͺ��̽��� ������, �л���������� ������������ ���
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

	// ������ ���̽��� ������, �л��̸��˻����� ���� ��ȸ ��, ���������ʴ� �̸� ��� X
	public static List<StudentResult> studentResultStudentNameNotOverlapTBL(String studentName) {
		List<StudentResult> list = new ArrayList<StudentResult>();
		// �����ͺ��̽��� ����
		Connection con = DBUtility.getConnection();
		String searchQuery = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		// �˻��� ���� �Է¹ޱ�(�˻��� ������ �������� �ο��ϱ�.)
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