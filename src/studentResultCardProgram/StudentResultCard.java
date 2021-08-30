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
			//ÇĞ»ı Á¤º¸ ÀÔ·Â
			case INPUT_DATA: studentResultInput(); break; 
			//ÇĞ»ı Á¤º¸ Á¶È¸
			case INQUIRY_DATA: studentResultInquiry(); break;
			//ÇĞ»ı Á¤º¸ ¼öÁ¤
			case UPDATE_DATA: studentResultUpdate(); break; 
			//ÇĞ»ı Á¤º¸ »èÁ¦
			case DELETE_DATA: studentResultDelete(); break; 
			//ÇĞ»ı Á¤º¸ °Ë»ö
			case SEARCH_DATA: studentResultSearch(); break;
			//ÇĞ»ı Á¤º¸ Á¤·Ä
			case SORT_DATA: studentResultSort(); break; 
			//»èÁ¦µÈ ÇĞ»ı Á¤º¸
			case WASTEBASKET_DATA : studentResultWastebasket(); break; 
			//ÇÁ·Î±×·¥ Á¾·á
			case FINISH: flag = true; break; 
			default : System.out.println("ÀÔ·Â°¡´ÉÇÑ ¼ıÀÚ ¹üÀ§¸¦ ¹ş¾î³µ½À´Ï´Ù. ´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä."); break;
			}//end of switch
		}//end of while
		System.out.println("ÇÁ·Î±×·¥ÀÌ Á¾·áµÇ¾ú½À´Ï´Ù.");
	}//end of main
	//¸ŞÀÎ ¼±ÅÃ ¸Ş´º
	private static int showChoiceMenu() {
		int selectNumber = 0;
		boolean flag = false;
		while(!flag) {
			System.out.print("\n¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á");
			System.out.print("\n1.ÇĞ»ı Á¤º¸ ÀÔ·Â 2.ÇĞ»ı Á¤º¸ Á¶È¸ 3.ÇĞ»ı Á¤º¸ ¼öÁ¤ 4.ÇĞ»ı Á¤º¸ »èÁ¦ 5.ÇĞ»ı Á¤º¸ °Ë»ö 6.ÇĞ»ı Á¤º¸ Á¤·Ä 7.»èÁ¦ÇÑ ÇĞ»ı Á¤º¸ 8.ÇÁ·Î±×·¥ Á¾·á");
			System.out.print("\n¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á");
			System.out.print("\n¹øÈ£¼±ÅÃ>>");
			try {
				selectNumber = Integer.parseInt(scan.nextLine());
			}catch(InputMismatchException e){
				System.out.println("¼ıÀÚ¸¸ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
				continue;
			}catch(Exception e) {
				System.out.println("¹®Á¦¹ß»ı, ´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä.");
				continue;
			}
			break;
		}
		return selectNumber;
	}
	//ÇĞ»ı Á¤º¸ ÀÔ·Â
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
			System.out.print("ÇĞ»ı¹øÈ£(8ÀÚ¸®)¸¦ ÀÔ·Â>>");
			studentNum = scan.nextLine();
			if(studentNum.length() != 8) {
				System.out.println("ÇĞ»ı¹øÈ£ ¾ç½ÄÀ» ÁöÄÑÁÖ¼¼¿ä.");
				continue;
			}
			boolean checkStudentNum = duplicateStudentNumCheck(studentNum);
			if(checkStudentNum == true) {
				System.out.println("Á¸ÀçÇÏ´Â ÇĞ»ı¹øÈ£ÀÔ´Ï´Ù. ´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä.");
				continue;
			}break;
		}//end of while
			while(true) {
			System.out.print("ÇĞ»ı ÀÌ¸§ ÀÔ·Â>>");
			studentName = scan.nextLine();
			boolean inputCheck = Pattern.matches("^[°¡-ÆR]*$", studentName);
			if(inputCheck == true) {
			}else {
				System.out.println("ÇÑ±Û¸¸ ÀÔ·ÂÇØÁÖ¼¼¿ä");
				continue;
			} 
			if(studentName.length() > 4 || studentName.length() < 2) {
				System.out.println("ÀÌ¸§ÀÔ·Â ¾ç½ÄÀ» ÁöÄÑÁÖ¼¼¿ä.");
				continue;
			}else {
	               break; 
	            }
			}//end of while
			while(true) {
				System.out.print("±¹¾îÁ¡¼ö ÀÔ·Â>>");
				try {
					korScore = Integer.parseInt(scan.nextLine());// Á¤¼ö ÀÔ·Â
				}
			     catch(Exception e) {
					System.out.println("¼ıÀÚ°¡ ¾Æ´Õ´Ï´Ù. ´Ù½Ã ÀÔ·Â!");
					continue;
			     }
				if(korScore < 0 || korScore >100) {
					System.out.println("Á¡¼öÀÔ·Â ¹üÀ§¸¦ ÁöÄÑÁÖ¼¼¿ä.");
					continue;
				}else {
					break;
				}
			}//end of while
			while(true) {
				System.out.print("¼öÇĞÁ¡¼ö ÀÔ·Â>>");
				try {
					mathScore = Integer.parseInt(scan.nextLine());// Á¤¼ö ÀÔ·Â
				}
			     catch(Exception e) {
					System.out.println("¼ıÀÚ°¡ ¾Æ´Õ´Ï´Ù. ´Ù½Ã ÀÔ·Â!");
					continue;
			     }
				if(mathScore < 0 || mathScore >100) {
					System.out.println("Á¡¼öÀÔ·Â ¾ç½ÄÀ» ÁöÄÑÁÖ¼¼¿ä.");
					continue;
				}else {
					break;
				}
			}//end of while
			while (true) {
				System.out.print("¿µ¾îÁ¡¼ö ÀÔ·Â>>");
				try {
					engScore = Integer.parseInt(scan.nextLine());// Á¤¼ö ÀÔ·Â
				} catch (Exception e) {
					System.out.println("¼ıÀÚ°¡ ¾Æ´Õ´Ï´Ù. ´Ù½Ã ÀÔ·Â!");
					continue;
				}
				if (engScore < 0 || engScore > 100) {
					System.out.println("Á¡¼öÀÔ·Â ¾ç½ÄÀ» ÁöÄÑÁÖ¼¼¿ä.");
					continue;
				}
				break;
			} // end of while
			StudentResult studentResult = new StudentResult(studentNum, studentName, korScore, mathScore, engScore,
					total, avg, grade);
			int count = StudentResultController.studentResultInputTBL(studentResult);

			if (count == 1) {
				System.out.println(studentResult.getStudentNum() + "´Ô »ğÀÔ¼º°ø");
			} else {
				System.out.println(studentResult.getStudentNum() + "´Ô »ğÀÔ½ÇÆĞ");
			}
		}

		// ÇĞ»ı ¹øÈ£ Áßº¹°ª Çã¿ë X
		private static boolean duplicateStudentNumCheck(String studentNum) {
			List<StudentResult> list = new ArrayList<StudentResult>();

			list = StudentResultController.studentResultStudentNumNotOverlapTBL(studentNum);
			if (list.size() >= 1) {
				return true;
			}
			return false;
		}

		// ÇĞ»ı Á¤º¸ Á¶È¸
		private static void studentResultInquiry() {
			List<StudentResult> list = new ArrayList<StudentResult>();

			list = StudentResultController.studentResultInquiryTBL();
			if (list.size() <= 0) {
				System.out.println("Ãâ·ÂÇÒ µ¥ÀÌÅÍ°¡ ¾ø½À´Ï´Ù.");
				return;
			}
		}

		// ÇĞ»ı Á¤º¸ ¼öÁ¤
		private static void studentResultUpdate() {
			int korScore = 0;
			int mathScore = 0;
			int engScore = 0;
			String studentNum = null;
			while (true) {
				System.out.print("¼öÁ¤ÇÒ ÇĞ»ı¹øÈ£(8ÀÚ¸®)¸¦ ÀÔ·Â>>");
				studentNum = scan.nextLine();
				if (studentNum.length() != 8) {
					System.out.println("ÇĞ»ı¹øÈ£ ¾ç½ÄÀ» ÁöÄÑÁÖ¼¼¿ä.");
					continue;
				}
				boolean checkStudentNum = duplicateStudentNumCheck(studentNum);
				if (checkStudentNum == false) {
					System.out.println("¹øÈ£¸¦ Àß¸ø ÀÔ·ÂÇß½À´Ï´Ù. ´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä.");
					continue;
				}
				break;
			} // end of while
			while (true) {
				System.out.print("±¹¾îÁ¡¼ö ¼öÁ¤>>");
				try {
					korScore = Integer.parseInt(scan.nextLine());// Á¤¼ö ÀÔ·Â
				} catch (Exception e) {
					System.out.println("¼ıÀÚ°¡ ¾Æ´Õ´Ï´Ù. ´Ù½Ã ÀÔ·Â!");
					continue;
				}
				if (korScore < 0 || korScore > 100) {
					System.out.println("Á¡¼öÀÔ·Â ¹üÀ§¸¦ ÁöÄÑÁÖ¼¼¿ä.");
					continue;
				} else {
					break;
				}
			} // end of while
			while (true) {
				System.out.print("¼öÇĞÁ¡¼ö ¼öÁ¤>>");
				try {
					mathScore = Integer.parseInt(scan.nextLine());// Á¤¼ö ÀÔ·Â
				} catch (Exception e) {
					System.out.println("¼ıÀÚ°¡ ¾Æ´Õ´Ï´Ù. ´Ù½Ã ÀÔ·Â!");
					continue;
				}
				if (mathScore < 0 || mathScore > 100) {
					System.out.println("Á¡¼öÀÔ·Â ¹üÀ§¸¦ ÁöÄÑÁÖ¼¼¿ä.");
					continue;
				} else {
					break;
				}
			} // end of while
			while (true) {
				System.out.print("¿µ¾îÁ¡¼ö ¼öÁ¤>>");
				try {
					engScore = Integer.parseInt(scan.nextLine());// Á¤¼ö ÀÔ·Â
				} catch (Exception e) {
					System.out.println("¼ıÀÚ°¡ ¾Æ´Õ´Ï´Ù. ´Ù½Ã ÀÔ·Â!");
					continue;
				}
				if (engScore < 0 || engScore > 100) {
					System.out.println("Á¡¼öÀÔ·Â ¹üÀ§¸¦ ÁöÄÑÁÖ¼¼¿ä.");
					continue;
				} else {
					break;
				}
			} // end of while
			int count = StudentResultController.studentResultUpdateTBL(studentNum, korScore, mathScore, engScore);
			if (count != 0) {
				System.out.println(studentNum + "¹ø ÇĞ»ı ¼öÁ¤¼º°ø");
			} else {
				System.out.println(studentNum + "¹ø ÇĞ»ı ¼öÁ¤½ÇÆĞ");
			}
		}

		// ÇĞ»ı Á¤º¸ »èÁ¦
		private static void studentResultDelete() {
			String studentNum = null;
			while (true) {
				System.out.print("»èÁ¦ÇÒ ÇĞ»ıÀÇ ÇĞ»ı¹øÈ£(8ÀÚ¸®)¸¦ ÀÔ·Â>>");
				studentNum = scan.nextLine();
				if (studentNum.length() != 8) {
					System.out.println("ÇĞ»ı¹øÈ£ ¾ç½ÄÀ» ÁöÄÑÁÖ¼¼¿ä.");
					continue;
				}
				boolean checkStudentNum = duplicateStudentNumCheck(studentNum);
				if (checkStudentNum != true) {
					System.out.println("Á¸ÀçÇÏÁö¾Ê´Â ÇĞ»ı¹øÈ£ÀÔ´Ï´Ù. ´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä.");
					continue;
				}
				break;
			} // end of while
			boolean flag = false;
			int count = StudentResultController.studentResultDeleteTBL(studentNum);
			if (count == 1) {
				flag = true;
				System.out.println(studentNum + "¹ø ÇĞ»ı »èÁ¦¿Ï·áÇß½À´Ï´Ù.");
			} else {
				System.out.println(studentNum + "¹ø ÇĞ»ı »èÁ¦½ÇÆĞÇß½À´Ï´Ù.");
			}
		}

		// ÇĞ»ı Á¤º¸ °Ë»ö
		private static void studentResultSearch() {
			// °Ë»öÇÒ ³»¿ë ÀÔ·Â¹Ş±â(°Ë»öÇÒ Á¶°ÇÀ» °³°³ÀÎÀÌ ºÎ¿©ÇÏ±â.)
			boolean flag = false;
			int selcetNumber = 0;
			final int NUM_SEARCH = 1, NAME_SEARCH = 2, SEARCH_FINISH = 3;
			String studentNum = null;
			String studentName = null;

			while (!flag) {
				// ¸Å´ºÃâ·Â¹× ¹øÈ£¼±ÅÃ

				selcetNumber = displayStudentResultSearchMenu();
				boolean checkStudentNum = duplicateStudentNumCheck(studentNum);
				boolean checkStudentName = duplicateStudentNameCheck(studentName);

				switch (selcetNumber) {
				case NUM_SEARCH:
					System.out.print("°Ë»öÇÒ ÇĞ»ıÇĞ¹øÀ» ÀÔ·ÂÇØÁÖ¼¼¿ä>>");
					studentNum = scan.nextLine();
					if (studentNum.length() != 8) {
						System.out.println("ÇĞ»ı¹øÈ£ ¾ç½ÄÀ» ÁöÄÑÁÖ¼¼¿ä.");
						continue;
					}
					if (checkStudentNum == false) {
						System.out.println("Á¸ÀçÇÏÁö¾Ê´Â ÇĞ»ı¹øÈ£ÀÔ´Ï´Ù. ´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä.");
						continue;
					}
					StudentResultController.studentNUM_SEARCH(studentNum);
					break;
				case NAME_SEARCH:
					System.out.print("°Ë»öÇÒ ÇĞ»ıÀÌ¸§À» ÀÔ·ÂÇØÁÖ¼¼¿ä>>");
					studentName = scan.nextLine();
					if (studentName.length() > 4 || studentName.length() < 2) {
						System.out.println("ÀÌ¸§ÀÔ·Â ¾ç½ÄÀ» ÁöÄÑÁÖ¼¼¿ä.");
						continue;
					}
					if (checkStudentNum == false) {
						System.out.println("Á¸ÀçÇÏÁö¾Ê´Â ÇĞ»ıÀÌ¸§ÀÔ´Ï´Ù. ´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä.");
						continue;
					}
					StudentResultController.studentNAME_SEARCH(studentName);
					break;
				case SEARCH_FINISH:
					flag = true;
					break;
				default:
					System.out.println("¹øÈ£°¡ ÃÊ°úµÇ¾ú½À´Ï´Ù");
					break;
				}// end of switch
			}

		}

		// ÇĞ»ı Á¤º¸ °Ë»ö ½Ã Àß¸øµÈ ÀÌ¸§ °Ë»ö Çã¿ëX
		private static boolean duplicateStudentNameCheck(String studentName) {
			List<StudentResult> list = new ArrayList<StudentResult>();

			list = StudentResultController.studentResultStudentNameNotOverlapTBL(studentName);
			if (list.size() >= 1) {
				return true;
			}
			return false;
		}

		// °Ë»ö ¹æ½Ä Ç¥Çö ¸Ş´º
		private static int displayStudentResultSearchMenu() {
			int selectNumber = 0;
			boolean flag = false;
			while (!flag) {

				System.out.print("¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á");
				System.out.print("\n°Ë»ö¼±ÅÃ 1.ÇĞ»ı¹øÈ£ºÎ°Ë»ö 2.ÀÌ¸§°Ë»ö 3.³ª°¡±â");
				System.out.print("\n¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á");
				System.out.print("\n¹øÈ£¼±ÅÃ>>");
				try {
					selectNumber = Integer.parseInt(scan.nextLine());
				} catch (InputMismatchException e) {
					System.out.println("¼ıÀÚ ÀÔ·Â¹üÀ§¸¦ ÃÊ°úÇÏ¿´½À´Ï´Ù.");
					continue;
				} catch (Exception e) {
					System.out.println("¼ıÀÚ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
					continue;
				}
				break;
			}
			return selectNumber;
		}

		// ÇĞ»ı Á¤º¸ Á¤·Ä
		private static void studentResultSort() {
			boolean flag = false;
			int selcetNumber = 0;
			final int STUDENT_NUM_ASC = 1, STUDENT_AVG_DESC = 2, FINISH = 3;
			boolean numberInputContitue = false;
			while (!flag) {
				// ¸Å´ºÃâ·Â¹× ¹øÈ£¼±ÅÃ

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
					System.out.println("¹øÈ£°¡ ÃÊ°úµÇ¾ú½À´Ï´Ù");
					break;
				}// end of switch
			}

		}

		// Á¤·Ä ¹æ½Ä Ç¥Çö ¸Ş´º
		private static int displayShowMenu() {
			int selcetNumber = 0;
			boolean flag = false;
			while (!flag) {

				System.out.println("¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á");
				System.out.println("1.ÇĞ¹øÁ¤·Ä 2.Æò±ÕÁ¡¼ö Á¤·Ä 3.³ª°¡±â");
				System.out.println("¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á");
				System.out.print("¹øÈ£¼±ÅÃ>>");
				try {
					// ¹øÈ£¼±ÅÃ
					selcetNumber = Integer.parseInt(scan.nextLine());
				} catch (InputMismatchException e) {
					System.out.println("¼ıÀÚÀÔ·Â¿ä¸Á");
					continue;
				} catch (Exception e) {
					System.out.println("¼ıÀÚÀÔ·Â¿ä¸Á ÀçÀÔ·Â¿ä¸Á");
					continue;
				}
				break;
			}
			return selcetNumber;

		}

		// »èÁ¦µÈ ÇĞ»ı Á¤º¸
		private static void studentResultWastebasket() {
			List<StudentResult> list = new ArrayList<StudentResult>();
			list = StudentResultController.studentResultWastebasketTBL();
		}

	}