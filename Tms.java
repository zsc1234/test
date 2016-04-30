package com.jv.p5;
 
 import java.util.Scanner;
 
 public class Tms{
 	Teacher[] Tea = new Teacher[3];
 	private int index;
 	/**
 	 * 添加教师信息
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
 	 * 查询所有教师信息
 	 */
 	public Teacher[] queryAll(){
 		Teacher[] demo = new Teacher[index];
 		System.arraycopy(Tea,0,demo,0,index);
 	    return demo;
 	}
 
     /**
 	 * 修改教师信息
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
 	 * 通过id删除教师信息
 	 */
 	public void deleteById(long id){
 		int n = getIndexById(id);
 		for(int i=n;i<index-1;i++){
 		    Tea[i]=Tea[i+1];
 		}
 		Tea[index--] = null;
 	}
 
     /**
 	 * 通过id查找教师信息
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
 	 * 通过id获取对象在数组中的索引
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
 		System.out.println("********教师信息管理系统********");
 	    System.out.println("*1.查询所有教师信息");
 		System.out.println("*2 录入教师信息");
 		System.out.println("*3 删除教师信息");
 		System.out.println("*4 通过id查找教师信息");
 		System.out.println("*5 修改教师信息");
 		System.out.println("*exit 退出系统！");
 		System.out.println("*help 获取帮助");
 		System.out.println("********************************");
 	}
 	public static void main(String[] args){
 	    Tms tms = new Tms();
 		tms.menu();
 		Scanner sc = new Scanner(System.in);
 		while(true){
 			System.out.print("请输入对应的指令：");
 			String a = sc.nextLine();
 			switch(a){
 			    case "1":
 					System.out.println("以下是所有教师的信息：");
 				    Teacher[] Tea = tms.queryAll();
 					for(Teacher tea : Tea){
 					    System.out.println(tea);
 					}
 					System.out.println("一共有"+tms.index+"名教师");
 					break;
 				case "2":
 					while(true){
 					    System.out.println("请输入教师的信息【id#name#age】或输入【break】返回上一级目录");
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
 					    System.out.println("保存成功");
 				}
 					break;
 				case "3":
 					while(true){
 					    System.out.println("请输入要删除的教师的【id】或输入【break】返回上一层目录");
 						String idTea = sc.nextLine();
 						if(idTea.equals("break")){
 						    break;
 						}
 						long id = Long.parseLong(idTea);
 						tms.deleteById(id);
 						System.out.println("删除成功");
 				}
 					break;
 				case "4":
 					while(true){
 					    System.out.println("请输入要查找的教师的【id】或输入【break】返回上一层目录");
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
 					    System.out.println("请输入要修改教师的【id】或输入【break】返回上一层目录");
 						String idTea = sc.nextLine();
					    if(idTea.equals("break")){
 					        break;
 					    }
 					    long id = Long.parseLong(idTea);
 						Teacher tea = tms.queryById(id);
 						if(tea==null){
 						    System.out.println("你要修改的教师不存在，请确认【id】后再输入！");
 							continue;
 						}
 						System.out.println("原信息为："+tea);
 						System.out.println("请输入新信息【name#age】：");
 						String str = sc.nextLine();
 						String[] strArr = str.split("#");
 						String name = strArr[0];
 						int age = Integer.parseInt(strArr[1]);
 						Teacher newtea = new Teacher(id,name,age);
 						tms.update(newtea);
 						System.out.println("修改成功");
 				}
 					break;
 				case "exit":
 					System.out.println("欢迎使用！");
 				    System.exit(0);
 				case "help":
 					tms.menu();
 					break;
 				default:
 					System.out.println("输入错误");
 			}
 		}
 	}
 } 