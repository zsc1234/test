package com.jv.p5;
 
 import java.util.Scanner;
 
 public class Tms{
 	Teacher[] Tea = new Teacher[3];
 	private int index;
 	/**
 	 * ��ӽ�ʦ��Ϣ
 	 */
 	public void save(Teacher teacher){
 	    if(index>=Tea.length){
 		    Teacher[] demo = new Teacher[Tea.length+3];
             System.arraycopy(Tea,0,demo,0,index);
 			Tea = demo;
 		}
 		Tea[index++] = teacher;
 	}
 
    /**
 	 * ��ѯ���н�ʦ��Ϣ
 	 */
 	public Teacher[] queryAll(){
 		Teacher[] demo = new Teacher[index];
 		System.arraycopy(Tea,0,demo,0,index);
 	    return demo;
 	}
 
     /**
 	 * �޸Ľ�ʦ��Ϣ
 	 */
 	public void update(Teacher teacher){
 		for(int i=0;i<index;i++){
 		    if(Tea[i].getId()==teacher.getId()){
 			    Tea[i].setName(teacher.getName());
 				Tea[i].setAge(teacher.getAge());
 			}
 		}
 	}
 
     /**
 	 * ͨ��idɾ����ʦ��Ϣ
 	 */
 	public void deleteById(long id){
 		int n = getIndexById(id);
 		for(int i=n;i<index-1;i++){
 		    Tea[i]=Tea[i+1];
 		}
 		Tea[index--] = null;
 	}
 
     /**
 	 * ͨ��id���ҽ�ʦ��Ϣ
 	 */
 	public Teacher queryById(long id){
 		int n = getIndexById(id);
 		if(n == -1){
 		    return null;
		}
 		else{
 	        return Tea[n];
 		}
 	}
 
     /**
 	 * ͨ��id��ȡ�����������е�����
 	 */
 	private int getIndexById(long id){
 	    int n = -1;
 		for(int i=0;i<Tea.length;i++){
 		    if(Tea[i].getId()==id){
 			    n = i;
 				break;
 			}
 		}
 		return n;
 	}
 
 	public void menu()
 	{
 		System.out.println("********��ʦ��Ϣ����ϵͳ********");
 	    System.out.println("*1.��ѯ���н�ʦ��Ϣ");
 		System.out.println("*2 ¼���ʦ��Ϣ");
 		System.out.println("*3 ɾ����ʦ��Ϣ");
 		System.out.println("*4 ͨ��id���ҽ�ʦ��Ϣ");
 		System.out.println("*5 �޸Ľ�ʦ��Ϣ");
 		System.out.println("*exit �˳�ϵͳ��");
 		System.out.println("*help ��ȡ����");
 		System.out.println("********************************");
 	}
 	public static void main(String[] args){
 	    Tms tms = new Tms();
 		tms.menu();
 		Scanner sc = new Scanner(System.in);
 		while(true){
 			System.out.print("�������Ӧ��ָ�");
 			String a = sc.nextLine();
 			switch(a){
 			    case "1":
 					System.out.println("���������н�ʦ����Ϣ��");
 				    Teacher[] Tea = tms.queryAll();
 					for(Teacher tea : Tea){
 					    System.out.println(tea);
 					}
 					System.out.println("һ����"+tms.index+"����ʦ");
 					break;
 				case "2":
 					while(true){
 					    System.out.println("�������ʦ����Ϣ��id#name#age�������롾break��������һ��Ŀ¼");
 				        String tea = sc.nextLine();
 					    if(tea.equals("break")){
 						    break;
 					    }
 					    String[] teaA = tea.split("#");
 					    Long id = Long.parseLong(teaA[0]);
 					    String name = teaA[1];
 					    int age = Integer.parseInt(teaA[2]);
 					    Teacher TeaA = new Teacher(id,name,age);
 					    tms.save(TeaA);
 					    System.out.println("����ɹ�");
 				}
 					break;
 				case "3":
 					while(true){
 					    System.out.println("������Ҫɾ���Ľ�ʦ�ġ�id�������롾break��������һ��Ŀ¼");
 						String idTea = sc.nextLine();
 						if(idTea.equals("break")){
 						    break;
 						}
 						long id = Long.parseLong(idTea);
 						tms.deleteById(id);
 						System.out.println("ɾ���ɹ�");
 				}
 					break;
 				case "4":
 					while(true){
 					    System.out.println("������Ҫ���ҵĽ�ʦ�ġ�id�������롾break��������һ��Ŀ¼");
 				        String idTea = sc.nextLine();
 					    if(idTea.equals("break")){
 					        break;
 					    }
 					    long id = Long.parseLong(idTea);
 					    Teacher tea = tms.queryById(id);
 					    System.out.println(tea==null?"sorry,not found":tea);
 				}
 					break;
 				case "5":
 				    while(true){
 					    System.out.println("������Ҫ�޸Ľ�ʦ�ġ�id�������롾break��������һ��Ŀ¼");
 						String idTea = sc.nextLine();
					    if(idTea.equals("break")){
 					        break;
 					    }
 					    long id = Long.parseLong(idTea);
 						Teacher tea = tms.queryById(id);
 						if(tea==null){
 						    System.out.println("��Ҫ�޸ĵĽ�ʦ�����ڣ���ȷ�ϡ�id���������룡");
 							continue;
 						}
 						System.out.println("ԭ��ϢΪ��"+tea);
 						System.out.println("����������Ϣ��name#age����");
 						String str = sc.nextLine();
 						String[] strArr = str.split("#");
 						String name = strArr[0];
 						int age = Integer.parseInt(strArr[1]);
 						Teacher newtea = new Teacher(id,name,age);
 						tms.update(newtea);
 						System.out.println("�޸ĳɹ�");
 				}
 					break;
 				case "exit":
 					System.out.println("��ӭʹ�ã�");
 				    System.exit(0);
 				case "help":
 					tms.menu();
 					break;
 				default:
 					System.out.println("�������");
 			}
 		}
 	}
 } 