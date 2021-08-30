package studentResultCardProgram;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;


public class StudentResultCard {	
	public static final int INPUT_DATA = 1,INQUIRY_DATA = 2,UPDATE_DATA = 3,DELETE_DATA = 4, SEARCH_DATA = 5, SORT_DATA = 6,WASTEBASKET_DATA = 7,FINISH = 8;
	public static final Scanner scan = new Scanner(System.in);
	public static void main(String[] args) throws IOException{
		
		boolean flag = false;
		int selectNumber = 0;
		//select menu
		while(!flag) {
			selectNumber = showChoiceMenu();
			
			switch(selectNumber) {
			//�л� ���� �Է�
			case INPUT_DATA: studentResultInput(); break; 
			//�л� ���� ��ȸ
			case INQUIRY_DATA: studentResultInquiry(); break;
			//�л� ���� ����
			case UPDATE_DATA: studentResultUpdate(); break; 
			//�л� ���� ����
			case DELETE_DATA: studentResultDelete(); break; 
			//�л� ���� �˻�
			case SEARCH_DATA: studentResultSearch(); break;
			//�л� ���� ����
			case SORT_DATA: studentResultSort(); break; 
			//������ �л� ����
			case WASTEBASKET_DATA : studentResultWastebasket(); break; 
			//���α׷� ����
			case FINISH: flag = true; break; 
			default : System.out.println("�Է°����� ���� ������ ������ϴ�. �ٽ� �Է����ּ���."); break;
			}//end of switch
		}//end of while
		System.out.println("���α׷��� ����Ǿ����ϴ�.");
	}//end of main
	//���� ���� �޴�
	private static int showChoiceMenu() {
		int selectNumber = 0;
		boolean flag = false;
		while(!flag) {
			System.out.print("\n����������������������������������������������������������������������������������������������������������������");
			System.out.print("\n1.�л� ���� �Է� 2.�л� ���� ��ȸ 3.�л� ���� ���� 4.�л� ���� ���� 5.�л� ���� �˻� 6.�л� ���� ���� 7.������ �л� ���� 8.���α׷� ����");
			System.out.print("\n����������������������������������������������������������������������������������������������������������������");
			System.out.print("\n��ȣ����>>");
			try {
				selectNumber = Integer.parseInt(scan.nextLine());
			}catch(InputMismatchException e){
				System.out.println("���ڸ� �Է����ּ���.");
				continue;
			}catch(Exception e) {
				System.out.println("�����߻�, �ٽ� �Է����ּ���.");
				continue;
			}
			break;
		}
		return selectNumber;
	}
	//�л� ���� �Է�
	private static void studentResultInput() {
		boolean flag = false;
		String studentNum = null;
		String studentName = null;
		int korScore = 0;
		int mathScore = 0;
		int engScore = 0;
		int total = 0;
		double avg = 0.0;
		String grade = null;
		while(true) {
			System.out.print("�л���ȣ(8�ڸ�)�� �Է�>>");
			studentNum = scan.nextLine();
			if(studentNum.length() != 8) {
				System.out.println("�л���ȣ ����� �����ּ���.");
				continue;
			}
			boolean checkStudentNum = duplicateStudentNumCheck(studentNum);
			if(checkStudentNum == true) {
				System.out.println("�����ϴ� �л���ȣ�Դϴ�. �ٽ� �Է����ּ���.");
				continue;
			}break;
		}//end of while
			while(true) {
			System.out.print("�л� �̸� �Է�>>");
			studentName = scan.nextLine();
			boolean inputCheck = Pattern.matches("^[��-�R]*$", studentName);
			if(inputCheck == true) {
			}else {
				System.out.println("�ѱ۸� �Է����ּ���");
				continue;
			} 
			if(studentName.length() > 4 || studentName.length() < 2) {
				System.out.println("�̸��Է� ����� �����ּ���.");
				continue;
			}else {
	               break; 
	            }
			}//end of while
			while(true) {
				System.out.print("�������� �Է�>>");
				try {
					korScore = Integer.parseInt(scan.nextLine());// ���� �Է�
				}
			     catch(Exception e) {
					System.out.println("���ڰ� �ƴմϴ�. �ٽ� �Է�!");
					continue;
			     }
				if(korScore < 0 || korScore >100) {
					System.out.println("�����Է� ������ �����ּ���.");
					continue;
				}else {
					break;
				}
			}//end of while
			while(true) {
				System.out.print("�������� �Է�>>");
				try {
					mathScore = Integer.parseInt(scan.nextLine());// ���� �Է�
				}
			     catch(Exception e) {
					System.out.println("���ڰ� �ƴմϴ�. �ٽ� �Է�!");
					continue;
			     }
				if(mathScore < 0 || mathScore >100) {
					System.out.println("�����Է� ����� �����ּ���.");
					continue;
				}else {
					break;
				}
			}//end of while
			while (true) {
				System.out.print("�������� �Է�>>");
				try {
					engScore = Integer.parseInt(scan.nextLine());// ���� �Է�
				} catch (Exception e) {
					System.out.println("���ڰ� �ƴմϴ�. �ٽ� �Է�!");
					continue;
				}
				if (engScore < 0 || engScore > 100) {
					System.out.println("�����Է� ����� �����ּ���.");
					continue;
				}
				break;
			} // end of while
			StudentResult studentResult = new StudentResult(studentNum, studentName, korScore, mathScore, engScore,
					total, avg, grade);
			int count = StudentResultController.studentResultInputTBL(studentResult);

			if (count == 1) {
				System.out.println(studentResult.getStudentNum() + "�� ���Լ���");
			} else {
				System.out.println(studentResult.getStudentNum() + "�� ���Խ���");
			}
		}

		// �л� ��ȣ �ߺ��� ��� X
		private static boolean duplicateStudentNumCheck(String studentNum) {
			List<StudentResult> list = new ArrayList<StudentResult>();

			list = StudentResultController.studentResultStudentNumNotOverlapTBL(studentNum);
			if (list.size() >= 1) {
				return true;
			}
			return false;
		}

		// �л� ���� ��ȸ
		private static void studentResultInquiry() {
			List<StudentResult> list = new ArrayList<StudentResult>();

			list = StudentResultController.studentResultInquiryTBL();
			if (list.size() <= 0) {
				System.out.println("����� �����Ͱ� �����ϴ�.");
				return;
			}
		}

		// �л� ���� ����
		private static void studentResultUpdate() {
			int korScore = 0;
			int mathScore = 0;
			int engScore = 0;
			String studentNum = null;
			while (true) {
				System.out.print("������ �л���ȣ(8�ڸ�)�� �Է�>>");
				studentNum = scan.nextLine();
				if (studentNum.length() != 8) {
					System.out.println("�л���ȣ ����� �����ּ���.");
					continue;
				}
				boolean checkStudentNum = duplicateStudentNumCheck(studentNum);
				if (checkStudentNum == false) {
					System.out.println("��ȣ�� �߸� �Է��߽��ϴ�. �ٽ� �Է����ּ���.");
					continue;
				}
				break;
			} // end of while
			while (true) {
				System.out.print("�������� ����>>");
				try {
					korScore = Integer.parseInt(scan.nextLine());// ���� �Է�
				} catch (Exception e) {
					System.out.println("���ڰ� �ƴմϴ�. �ٽ� �Է�!");
					continue;
				}
				if (korScore < 0 || korScore > 100) {
					System.out.println("�����Է� ������ �����ּ���.");
					continue;
				} else {
					break;
				}
			} // end of while
			while (true) {
				System.out.print("�������� ����>>");
				try {
					mathScore = Integer.parseInt(scan.nextLine());// ���� �Է�
				} catch (Exception e) {
					System.out.println("���ڰ� �ƴմϴ�. �ٽ� �Է�!");
					continue;
				}
				if (mathScore < 0 || mathScore > 100) {
					System.out.println("�����Է� ������ �����ּ���.");
					continue;
				} else {
					break;
				}
			} // end of while
			while (true) {
				System.out.print("�������� ����>>");
				try {
					engScore = Integer.parseInt(scan.nextLine());// ���� �Է�
				} catch (Exception e) {
					System.out.println("���ڰ� �ƴմϴ�. �ٽ� �Է�!");
					continue;
				}
				if (engScore < 0 || engScore > 100) {
					System.out.println("�����Է� ������ �����ּ���.");
					continue;
				} else {
					break;
				}
			} // end of while
			int count = StudentResultController.studentResultUpdateTBL(studentNum, korScore, mathScore, engScore);
			if (count != 0) {
				System.out.println(studentNum + "�� �л� ��������");
			} else {
				System.out.println(studentNum + "�� �л� ��������");
			}
		}

		// �л� ���� ����
		private static void studentResultDelete() {
			String studentNum = null;
			while (true) {
				System.out.print("������ �л��� �л���ȣ(8�ڸ�)�� �Է�>>");
				studentNum = scan.nextLine();
				if (studentNum.length() != 8) {
					System.out.println("�л���ȣ ����� �����ּ���.");
					continue;
				}
				boolean checkStudentNum = duplicateStudentNumCheck(studentNum);
				if (checkStudentNum != true) {
					System.out.println("���������ʴ� �л���ȣ�Դϴ�. �ٽ� �Է����ּ���.");
					continue;
				}
				break;
			} // end of while
			boolean flag = false;
			int count = StudentResultController.studentResultDeleteTBL(studentNum);
			if (count == 1) {
				flag = true;
				System.out.println(studentNum + "�� �л� �����Ϸ��߽��ϴ�.");
			} else {
				System.out.println(studentNum + "�� �л� ���������߽��ϴ�.");
			}
		}

		// �л� ���� �˻�
		private static void studentResultSearch() {
			// �˻��� ���� �Է¹ޱ�(�˻��� ������ �������� �ο��ϱ�.)
			boolean flag = false;
			int selcetNumber = 0;
			final int NUM_SEARCH = 1, NAME_SEARCH = 2, SEARCH_FINISH = 3;
			String studentNum = null;
			String studentName = null;

			while (!flag) {
				// �Ŵ���¹� ��ȣ����

				selcetNumber = displayStudentResultSearchMenu();
				boolean checkStudentNum = duplicateStudentNumCheck(studentNum);
				boolean checkStudentName = duplicateStudentNameCheck(studentName);

				switch (selcetNumber) {
				case NUM_SEARCH:
					System.out.print("�˻��� �л��й��� �Է����ּ���>>");
					studentNum = scan.nextLine();
					if (studentNum.length() != 8) {
						System.out.println("�л���ȣ ����� �����ּ���.");
						continue;
					}
					if (checkStudentNum == false) {
						System.out.println("���������ʴ� �л���ȣ�Դϴ�. �ٽ� �Է����ּ���.");
						continue;
					}
					StudentResultController.studentNUM_SEARCH(studentNum);
					break;
				case NAME_SEARCH:
					System.out.print("�˻��� �л��̸��� �Է����ּ���>>");
					studentName = scan.nextLine();
					if (studentName.length() > 4 || studentName.length() < 2) {
						System.out.println("�̸��Է� ����� �����ּ���.");
						continue;
					}
					if (checkStudentNum == false) {
						System.out.println("���������ʴ� �л��̸��Դϴ�. �ٽ� �Է����ּ���.");
						continue;
					}
					StudentResultController.studentNAME_SEARCH(studentName);
					break;
				case SEARCH_FINISH:
					flag = true;
					break;
				default:
					System.out.println("��ȣ�� �ʰ��Ǿ����ϴ�");
					break;
				}// end of switch
			}

		}

		// �л� ���� �˻� �� �߸��� �̸� �˻� ���X
		private static boolean duplicateStudentNameCheck(String studentName) {
			List<StudentResult> list = new ArrayList<StudentResult>();

			list = StudentResultController.studentResultStudentNameNotOverlapTBL(studentName);
			if (list.size() >= 1) {
				return true;
			}
			return false;
		}

		// �˻� ��� ǥ�� �޴�
		private static int displayStudentResultSearchMenu() {
			int selectNumber = 0;
			boolean flag = false;
			while (!flag) {

				System.out.print("�������������������������������������");
				System.out.print("\n�˻����� 1.�л���ȣ�ΰ˻� 2.�̸��˻� 3.������");
				System.out.print("\n�������������������������������������");
				System.out.print("\n��ȣ����>>");
				try {
					selectNumber = Integer.parseInt(scan.nextLine());
				} catch (InputMismatchException e) {
					System.out.println("���� �Է¹����� �ʰ��Ͽ����ϴ�.");
					continue;
				} catch (Exception e) {
					System.out.println("���ڸ� �Է����ּ���.");
					continue;
				}
				break;
			}
			return selectNumber;
		}

		// �л� ���� ����
		private static void studentResultSort() {
			boolean flag = false;
			int selcetNumber = 0;
			final int STUDENT_NUM_ASC = 1, STUDENT_AVG_DESC = 2, FINISH = 3;
			boolean numberInputContitue = false;
			while (!flag) {
				// �Ŵ���¹� ��ȣ����

				selcetNumber = displayShowMenu();

				switch (selcetNumber) {
				case STUDENT_NUM_ASC:
					StudentResultController.studentNum_ASC();
					break;
				case STUDENT_AVG_DESC:
					StudentResultController.studentAvg_DESC();
					break;
				case FINISH:
					flag = true;
					break;
				default:
					System.out.println("��ȣ�� �ʰ��Ǿ����ϴ�");
					break;
				}// end of switch
			}

		}

		// ���� ��� ǥ�� �޴�
		private static int displayShowMenu() {
			int selcetNumber = 0;
			boolean flag = false;
			while (!flag) {

				System.out.println("�����������������������������");
				System.out.println("1.�й����� 2.������� ���� 3.������");
				System.out.println("�����������������������������");
				System.out.print("��ȣ����>>");
				try {
					// ��ȣ����
					selcetNumber = Integer.parseInt(scan.nextLine());
				} catch (InputMismatchException e) {
					System.out.println("�����Է¿��");
					continue;
				} catch (Exception e) {
					System.out.println("�����Է¿�� ���Է¿��");
					continue;
				}
				break;
			}
			return selcetNumber;

		}

		// ������ �л� ����
		private static void studentResultWastebasket() {
			List<StudentResult> list = new ArrayList<StudentResult>();
			list = StudentResultController.studentResultWastebasketTBL();
		}

	}