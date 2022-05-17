import java.io.*;
public class Library{
	int bookcount;
	int usercount;
	java.util.Scanner scan;

	String user_fn = "user.bin";
	String book_fn;
	
	Book on_memory_book;
	User on_memory_user;

	Library(String fn){ 	
		this.book_fn = fn;
		count_check();
	}
	
	void user_menu() {
		boolean loop=true;
		scan = new java.util.Scanner(System.in);
		int menu_num;
		while(loop) {
			System.out.println("***********************");
			System.out.println("  1. ����  ");
			System.out.println("  2. �ݳ�  ");
			System.out.println("  3. �˻�  ");
			System.out.println("  4. ����  ");
			System.out.println("***********************");
			System.out.print("input menu number : ");
			menu_num=Integer.parseInt(scan.nextLine());		
			switch (menu_num) {
				case 1:
					borrowbook();
					break;
				case 2:
					returnbook();
					break;
				case 3:
					book_find();
					break;
				case 4 :
					loop=false;
					break;
				default:
					System.out.println("The menu number is Wrong!!!");	
					break;

			}	
		}
	}
	
	void admin_menu() {
		scan = new java.util.Scanner(System.in);
		boolean loop=true;
		int menu_num;
		while(loop) {
			System.out.println("***********************");
			System.out.println("  1. ��������  ");
			System.out.println("  2. ����ڰ���  ");
			System.out.println("  3. ���α�����  ");
			System.out.println("***********************");
			System.out.print("input menu number : ");
			menu_num=Integer.parseInt(scan.nextLine());		
			switch (menu_num) {
				case 1:
					book_set();
					break;
				case 2:
					user_set();
					break;
				case 3 :
					loop=false;
					System.out.println("���α׷� ����");
					break;
				default:
					System.out.println("The menu number is Wrong!!!");
					break;
			}
		}
	}

	void user_set() {
		int menu_num = 0;
		scan = new java.util.Scanner(System.in);
		boolean loop=true;
		while(loop) {
		System.out.println("***********************");
		System.out.println("  1. ������߰�  ");
		System.out.println("  2. ���������  ");
		System.out.println("  3. ����ڸ��  ");
		System.out.println("  4. ����  ");
		System.out.println("***********************");
		System.out.print("input menu number : ");
		menu_num=Integer.parseInt(scan.nextLine());
		
		switch (menu_num) {
			case 1:
				userregister();
				break;
			case 2:
				userremover();
				break;
			case 3:
				printAllUser();
				break;
			case 4 :
				loop=false;
				break;
			default:
				System.out.println("The menu number is Wrong!!!");
		}
		}
	}
	
	void book_set() {
		int menu_num = 0;
		scan = new java.util.Scanner(System.in);
		boolean loop=true;
		while(loop) {
		System.out.println("***********************");
		System.out.println("  1. �����߰�  ");
		System.out.println("  2. ��������  ");
		System.out.println("  3. �������  ");
		System.out.println("  4. ����  ");
		System.out.println("***********************");
		System.out.print("input menu number : ");
		menu_num=Integer.parseInt(scan.nextLine());
		
		switch (menu_num) {
			case 1:
				bookregister();
				break;
			case 2:
				bookremover();
				break;
			case 3:
				printAllBook();
				break;
			case 4 :
				loop=false;
				break;
			default:
				System.out.println("The menu number is Wrong!!!");
				break;
		}
		}
	}		
	
	void book_find() {
		boolean lengthTest = true;
		int book_pos = 0;
		while(lengthTest) {
			book_pos = searchBook(javax.swing.JOptionPane.showInputDialog("ã�� ������ �̸� �Ǵ� �ε��� ��ȣ�� �Է��ϼ��� "));
			if(book_pos == -1) {
				System.out.println("�������� �ʴ� ���� �Դϴ�. ���Է� ���ּ���.");
			}
			else
				lengthTest = false;
		}
		getBook(book_pos);
		
		System.out.printf("    ----- �˻���� -----   \n");
		System.out.printf("%5s %31s %6s %4s\n", "�ε���", "������", "���⿩��", "������");
		System.out.printf("%4d %21s %6s %4d\n", on_memory_book.index, on_memory_book.name, on_memory_book.islend, on_memory_book.lender);
			
	}
	
	int searchBook(String query) {
		//�̸��� �ߺ��� ��� ��� ó������ ����� 
		count_check();
		try {
			int index = Integer.parseInt(query);
			//System.out.println("�ε��� �˻�");
			for(int i = 0; i < bookcount;i++) {
				getBook(i);
				if(on_memory_book.index == index)
					return i;
			}
		}
		catch (NumberFormatException e) {
			//System.out.println("������˻�");
			for(int i = 0; i < bookcount;i++) {
				getBook(i);
				if(on_memory_book.name.compareTo(query) == 0)
					return i;
			}
		}
		return -1;	
	}
	
	int searchUser(int index) {
		count_check();
		//System.out.println("�ε��� �˻�");
		for(int i = 0; i < usercount;i++) {
			getUser(i);
			if(on_memory_user.idnum == index)
				return i;
		}
		return -1;	
	}
	
	void printAllBook() {
		count_check();
		System.out.printf("    ----- ������� -----   \n");
		System.out.printf("%4s %4s %31s %6s %4s\n", "No.", "�ε���", "������", "���⿩��", "������");
		for(int i =0, j = 1; i < bookcount; i++) {
			getBook(i);
			if(on_memory_book.index != 0)
				System.out.printf("%4d %4d %21s %6s %4d\n", j++, on_memory_book.index, on_memory_book.name, on_memory_book.islend, on_memory_book.lender);
		}
	}
	
	void getUser(int user_pos) {
		byte [] buf = new byte [50];
		try {
			RandomAccessFile f_in = new RandomAccessFile(user_fn, "r");
			f_in.seek(50*user_pos);
			f_in.read(buf);
			f_in.close();
			if(!(buf[0] == 0 && buf[1] == 0  && buf[2] == 0)) {
				if(buf[28] == '*')//Faculty
					on_memory_user = new Faculty(buf);
				else //Student
					on_memory_user = new Student(buf);
			}
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	void printAllUser() {
		count_check();
		String usertype;
		System.out.printf("    ----- ������� -----   \n");
		System.out.printf("%4s %4s %11s %23s %11s %8s\n", " No", "ID", "����", "����ó", "�׷�", "�뿩�Ǽ�");
		
		for(int i =0, j = 1; i < usercount; i++) {
			getUser(i);
			if(on_memory_user instanceof Student)
				usertype = "Student";
			else
				usertype = "Faculty";
			if(on_memory_user.idnum != 0)
				System.out.printf("%4d %4s %12s %15s %8s %7d \n", j++, on_memory_user.idnum, on_memory_user.name, on_memory_user.phonenum, usertype, on_memory_user.lend);
		}

	}
	
	void getBook(int book_pos) {
		byte [] buf = new byte [50];
		try {
			RandomAccessFile f_in = new RandomAccessFile(book_fn, "rw");
			f_in.seek(50*book_pos);
			f_in.read(buf);
			f_in.close();
			if(!(buf[0] == 0 && buf[1] == 0  && buf[2] == 0)) {
				on_memory_book = new Book(buf);
			}
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	void saveBook(int book_pos){
		try {
			RandomAccessFile out = new RandomAccessFile(book_fn, "rw");
			out.seek(50*book_pos);
			out.write(on_memory_book.toByte());
			out.close();
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
			
		}
	}
	
	void saveUser(int User_pos){
		try {
			RandomAccessFile out = new RandomAccessFile(user_fn, "rw");
			out.seek(50*User_pos);
			out.write(on_memory_user.tobyte());
			out.close();
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
			
		}
	}
	
	void bookregister(){
		Book tmp;
		while(true) {
			tmp = new Book();
			if(searchBook(tmp.index+"") == -1)
				break;
			else
				System.out.println("������ �ε����� �ߺ��Ǿ����ϴ�. ���Է����ּ���.");
		}
		
		int pos;
		try {
			if((pos=searchBook("000")) == -1) {
				FileOutputStream out = new FileOutputStream(book_fn,true);
				out.write(tmp.toByte());
				out.close();
			}
			else {
				RandomAccessFile out = new RandomAccessFile(book_fn,"rw");
				out.seek(50*pos);
				out.write(tmp.toByte());
				out.close();
			}
			System.out.println("������ ��ϵǾ����ϴ�.");
				
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}	
	}
	
	void bookremover(){
		int index = searchBook(javax.swing.JOptionPane.showInputDialog("������ ������(�ε���)�Է� "));
		if(index != -1) {
			try {
				byte [] deletebyte = new byte[50];
				for(int i = 0; i < 3;i++)
					deletebyte[i] = (byte)'0';
				for(int i = 3; i < 50;i++)
					deletebyte[i] = '*';
				RandomAccessFile out = new RandomAccessFile(book_fn,"rw");
				out.seek(50*index);
				out.write(deletebyte);
				out.close();
			}
			catch(IOException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("���� ����!");
		}
		else {
			System.out.println("�Է� ����� �ش��ϴ� ������ �����ϴ�.");
		}
	}

	void count_check(){
		try {
			RandomAccessFile f_in = new RandomAccessFile(book_fn, "rw");
			bookcount =  (int)(f_in.length() / 50);
			f_in.close();
			f_in = new RandomAccessFile(user_fn, "r");
			usercount =  (int)(f_in.length() / 50);
			f_in.close();
			if(bookcount == 0)
				System.out.println("������ ���� 0�� �Դϴ�!!! ������ ����� �ּ���!");
			else if(usercount == 0)
				System.out.println("������� ���� 0�� �Դϴ�!!! �����ڸ� ����� �ּ���!");

		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}

	
	void userregister(){
		int index = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("����� ����� ���� �Է� (�л� : '1', ������ : '2')"));
		User usr;
		if(index == 1)
			while(true) {
				usr = new Student();
				if(searchUser(usr.idnum) == -1)
					break;
				else
					System.out.println("������� ID��ȣ�� �ߺ��Ǿ����ϴ�. ���Է����ּ���.");
			}
		else if((index == 2))
			while(true) {
				usr = new Faculty();
				if(searchUser(usr.idnum) == -1)
					break;
				else
					System.out.println("������� ID��ȣ�� �ߺ��Ǿ����ϴ�. ���Է����ּ���.");
			}		
		else {
			System.out.println("�߸��� �� �Է�. �������� ���ư��ϴ�.");
			return;
		}
		
		int pos;
		try {
			if((pos=searchUser(000)) == -1) {
				FileOutputStream out = new FileOutputStream(user_fn,true);
				out.write(usr.tobyte());
				out.close();
			}
			else {
				RandomAccessFile out = new RandomAccessFile(user_fn,"rw");
				out.seek(50*pos);
				out.write(usr.tobyte());
				out.close();
			}
			System.out.println("����ڰ� ��ϵǾ����ϴ�.");
				
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}	
	}
	
	void userremover(){
		int idnum = searchUser(Integer.parseInt(javax.swing.JOptionPane.showInputDialog("������ ���� ID��ȣ �Է� ")));
		if(idnum != -1) {
			try {
				byte [] deletebyte = new byte[50];
				for(int i = 0; i < 3;i++)
					deletebyte[i] = (byte)'0';
				for(int i = 3; i < 50;i++)
					deletebyte[i] = '*';
				RandomAccessFile out = new RandomAccessFile(user_fn,"rw");
				out.seek(50*idnum);
				out.write(deletebyte);
				out.close();
			}
			catch(IOException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("���� ����!");
		}
		else {
			System.out.println("�ش��ϴ� ID��ȣ�� �����ϴ�.");
		}
	}
	
	
	
	
	void borrowbook(){
		boolean lengthTest = true;
		int book_pos = 0, user_pos = 0;
		while(lengthTest) {
			book_pos = searchBook(javax.swing.JOptionPane.showInputDialog("�뿩�� ������ �̸� �Ǵ� �ε��� ��ȣ�� �Է��ϼ��� "));
			if(book_pos == -1) {
				System.out.println("�������� �ʴ� ���� �Դϴ�. ���Է� ���ּ���.");
			}
			else
				lengthTest = false;
		}
		
		lengthTest = true;
		while(lengthTest) {
			user_pos = searchUser(Integer.parseInt(javax.swing.JOptionPane.showInputDialog("User ID�� �Է��� �ּ���")));
			if(user_pos == -1) {
				System.out.println("�������� �ʴ� ID �Դϴ�. ���Է� ���ּ���.");
			}
			else
				lengthTest = false;
		}
		
		if((user_pos != -1) && (book_pos != -1)) {
			getBook(book_pos);
			getUser(user_pos);
			if(check_per(false, on_memory_user.idnum)) {
				on_memory_book.lendBook(on_memory_user.idnum);
				on_memory_user.lend++;
				saveBook(book_pos);
				saveUser(user_pos);
				System.out.printf("����Ǿ����ϴ�. ������ : %s , ������ : %s\n", on_memory_book.name,on_memory_user.name);
			}
			else {
				if(check_per(true, 0)) {
					String qus = javax.swing.JOptionPane.showInputDialog("�ش� ������ ������ �����մϴ�. �����Ͻðڽ��ϱ�?(���� : 'yes')");
					if(qus.compareTo("yes") == 0)
						reserve_book(book_pos);
				}
				
			}
		}
		else
			System.out.printf("SYSTEM ���� �߻�!!!");
		
	}
	
	void returnbook(){
		boolean lengthTest = true;
		int book_pos = 0, user_pos = 0;;
		while(lengthTest) {
			book_pos = searchBook(javax.swing.JOptionPane.showInputDialog("�ݳ��� ������ �̸� �Ǵ� �ε��� ��ȣ�� �Է��ϼ��� "));
			if(book_pos == -1) {
				System.out.println("�������� �ʴ� ���� �Դϴ�. ���Է� ���ּ���.");
			}
			else
				lengthTest = false;
		}
		if(on_memory_book.islend) {
			getBook(book_pos);
			user_pos = searchUser(on_memory_book.lender);
			getUser(user_pos);
			on_memory_user.lend--;;
			on_memory_book.returnBook();
			saveBook(book_pos);
			saveUser(user_pos);
			System.out.printf("������ '%s'�� �ݳ��Ǿ����ϴ�.\n",on_memory_book.name);
		}
		else
			System.out.println("�ش� ������ ������� �ʾҽ��ϴ�!");
		
		
	}
	
	void reserve_book(int book_pos) {
		on_memory_book.reservate_book(on_memory_user.idnum);
		saveBook(book_pos);
	}
	
	boolean check_per(boolean wait, int id) {
		int [] lend_max = {0,5,5,5,5,9,9};
		//10�� �̻��� lend�� 2����Ʈ�� �ؾ���!
		//�������� ���߰ڴµ�...
		//int [] wait_max = {0,3,3,3,3,5,5};
		if(wait) {
			//���� ó�� - ���� X, å�� �ִ� ���� ���ɼ� 3�ʰ� X, ������ ���� ���� �ִ��
			if(on_memory_user instanceof Student) {
				Student stu = (Student)on_memory_user;
				if(stu.leave) { //�������̸� ����Ұ�
					return false;
				}
			}
			
			//if(on_memory_user.lend >= wait_max[on_memory_user.grade]) {
				//System.out.printf("������ �� �ִ� �ִ� �Ǽ��� �ʰ��Ͽ����ϴ�. (���� ����Ǽ� : %d, �ִ� ����Ǽ� : %d)\n",on_memory_user.lend, wait_max[on_memory_user.grade]);		
			//}
			
			if(on_memory_book.waiter[0] != 0 && on_memory_book.waiter[1] != 0 && on_memory_book.waiter[2] != 0) {//���� ����
				return false;
			}
			return true;
		}
		else {
			//���� ó�� - �̹� ��������?false, ������ �ִ� �뿩��
			if(on_memory_user.lend >= lend_max[on_memory_user.grade]) { //�̹� �ִ� �뿩������ �Ѿ��� ��� ����ȵ�
				System.out.printf("������ �� �ִ� �ִ� �Ǽ��� �ʰ��Ͽ����ϴ�. (���� �뿩�Ǽ� : %d, �ִ� �뿩�Ǽ� : %d)\n",on_memory_user.lend, lend_max[on_memory_user.grade]);
				return false;
				}
			if(on_memory_book.iswait && on_memory_book.waiter[0] != id) { //������ �Ǿ��ִ� �����Դϴ�.
				System.out.println("�ش� ������ ����Ǿ��־�, ������ �� �����ϴ�.");
				return false;		
			}
			if(on_memory_book.islend) { //�̹� �뿩���� ��� ����ȵ�
				System.out.println("�ش� ������ ���� �뿩�� �Դϴ�.");
				return false;		
			}
			
			return true;
		}
	}
	
	
}
