public class Library_main {
//������ ���� 6�ڸ��� ����
//�Ͽ� �ݳ����� �ֱ�
//�����͸� ���Ͽ� ������� ��, ������ ���ѿ� ���� �Է��ؾ� �ؼ� tobyte()�� ����.
	public static void main(String[] args) {
		java.util.Scanner in_m=new java.util.Scanner(System.in);
		Library lib = new Library("book.bin");
		System.out.print("User --> 1, Admin --> 2 : ");
		int user_number=Integer.parseInt(in_m.nextLine());
		
		switch(user_number) {
			case 1 :
				lib.user_menu();
				break;
			case 2 :
				lib.admin_menu();
				break;
			default :
				System.out.println("�ý��� ����. �߸��� ���� �Է�");
				break;
		}
		
		in_m.close();
	}

}
