package Algo;

import java.util.Arrays;
import java.util.Scanner;

public class OrAlgorithm {
	//max max
	static int player1Sum=0;
	static int player2Sum=0;
	static int diffrence=0;
	static int a=0;
	static int b=0;

	//max min
	static int player3Sum=0;
	static int player4Sum=0;
	static int diffrence2=0;
	static int a2=0;
	static int b2=0;


	//check max,max sorting ~ max,min sort
	public static void printTheBestSolution(int[] player1,int[] player2,int[] player3,int[] player4){
		if(diffrence<=diffrence2) {
			System.out.println("the best diffrence was(max max): "+diffrence);
			System.out.println("one side (winner situation) "+Arrays.toString(player1));
			System.out.println("side two (winner situation) "+Arrays.toString(player2));	
		}
		else {
			System.out.println("the best diffrence was (max min): "+diffrence2);
			System.out.println("one side (winner situation) "+Arrays.toString(player3));
			System.out.println("side two (winner situation) "+Arrays.toString(player4));
		}


	}

	//find the max element in the list of numbers
	static int findMax(int arr[]) {
		int max=(-1)*Integer.MAX_VALUE;
		for(int i=0;i<arr.length;i++) {
			if((arr[i]>max)&&(arr[i]!=Integer.MAX_VALUE)) {max=arr[i];}
		}
		return max;
	}


	//find the min element in the list of numbers
	static int findMin(int arr[]) {
		int min=Integer.MAX_VALUE;
		for(int i=0;i<arr.length;i++) {
			if((arr[i]<min)&&(arr[i]!=0)) {min=arr[i]; }
		}
		return min;
	}

	//after the element has been given to 1 side, make him 0. netrally
	static void remove(int arr[],int num) {
		for(int i=0;i<arr.length;i++) {
			if(arr[i]==num) {
				arr[i]=0;	break;
			}
		}
	}


	//check if swap 1 by 1 elements can be better situation. if yes do the swap
	static int swapCurrentAndSum(int arr1[],int arr2[],int i,int j){
		int arr1Sum=0;
		int arr2Sum=0;
		int totalSum=0;
		//swap the elements and calculate
		int temp=arr1[i];
		arr1[i]=arr2[j];
		arr2[j]=temp;
		//done to swap , now calculating each temporary sum
		for(int t=0;t<arr1.length;t++) {
			arr1Sum=arr1Sum+arr1[t];
			arr2Sum=arr2Sum+arr2[t];
		}
		totalSum=Math.abs(arr1Sum-arr2Sum); // sum after the swap
		//swap agian
		temp=arr1[i];
		arr1[i]=arr2[j];
		arr2[j]=temp;
		//return the temp sum after the swap
		return totalSum;
	} 

	static int maximumSize(int arr1[],int arr2[]) {
		int size=arr1.length;
		if(arr2.length>size) {size=arr2.length;}
		return size;
	}

	static void fix(int arr1[],int arr2[]) {
		for(int u=0;u<arr1.length;u++) {
			for(int t=0;t<arr1.length;t++) {
				//a is the size of element inserted to player1 sum
				for(int i=0;i<arr1.length;i++) {
					//b is the size of elements that inserted to player2 sum
					for(int j=0;j<arr2.length;j++) {
						//check if swap elements help to the diffrence between the sum of the players to get low diffrence
						if(swapCurrentAndSum(arr1,arr2,i,j)<diffrence) {
							player1Sum=(player1Sum-arr1[i]+arr2[j]);
							player2Sum=(player2Sum-arr2[j]+arr1[i]);
							int temp=arr1[i];
							arr1[i]=arr2[j];
							arr2[j]=temp;
							//update the better diffrence
							diffrence=Math.abs(player1Sum-player2Sum);

						}
					}
				}
			}
		}
	}


	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("enter size of array: ");
		int size=sc.nextInt();



		//situation 1
		int list[]=new int[size];//max max
		int player1[]=new int[size];
		int player2[]=new int[size];




		//init the list with numbers
		for(int i=0;i<list.length;i++) {
			System.out.println("choose a number: ");
			list[i]=sc.nextInt(); 
		}
		//print the list of numbers


		//situation 2
		int list2[]=new int[size];//max min
		int player3[]=new int[size];
		int player4[]=new int[size];

		for(int i=0;i<list2.length;i++) {
			System.out.println("choose a number: ");
			list2[i]=list[i]; 
		}


		System.out.println();
		System.out.println("start");

		System.out.println("the array in the start of the game: "+Arrays.toString(list));
		System.out.println("the array2 in the start of the game: "+Arrays.toString(list2));


		//initialize the players 1,2 array greedy method
		for(int i=0;i<list.length;i++) {

			//check if the sum of player1 -player2 sum is bigger than the diffrence
			if((player1Sum-player2Sum)<findMax(list)) {
				player1Sum=player1Sum+findMax(list);
				diffrence=Math.abs(player1Sum-player2Sum);
				player1[i]=findMax(list);
				remove(list,findMax(list));
				a++;
			}
			else { 
				player2Sum=player2Sum+findMin(list);
				diffrence=Math.abs(player1Sum-player2Sum);
				player2[i]=findMin(list);
				remove(list,findMin(list));
				b++;
			}
		}//end init




		//initialize the players 3,4 array2 greedy method
		for(int i=0;i<list2.length;i++) {

			//check if the sum of player3 -player4 sum is bigger than the diffrence2
			if((player3Sum-player4Sum)<findMax(list2)) {
				player3Sum=player3Sum+findMax(list2);
				diffrence2=Math.abs(player3Sum-player4Sum);
				player3[i]=findMax(list2);
				remove(list2,findMax(list2));
				a2++;
			}
			else { 
				player4Sum=player4Sum+findMax(list2);
				diffrence2=Math.abs(player3Sum-player4Sum);
				player4[i]=findMax(list2);
				remove(list2,findMax(list2));
				b2++;
			}
		}//end init2


		System.out.println();
		System.out.println("situation 1");

		//max to player1 , max to player2
		System.out.println("the diffrence befor fix of max max: "+diffrence);
		System.out.println("the array of player1 befor the fixing: "+Arrays.toString(player1));
		System.out.println("the array of player2 befor the fixing: "+Arrays.toString(player2));	
		System.out.println();		
		fix(player1,player2);
		System.out.println("the diffrence after fix of max max: "+diffrence);
		System.out.println("player1 numbers after distributing"+Arrays.toString(player1));
		System.out.println("player2 numbers after distributing"+Arrays.toString(player2));	
		//end max player1 ,max player2


		System.out.println();
		System.out.println("situation 2");

		//max to player1,min to player2
		System.out.println("the diffrence befor fix of max min: "+diffrence2);
		System.out.println("the array of player3 befor the fixing: "+Arrays.toString(player3));
		System.out.println("the array of player4 befor the fixing: "+Arrays.toString(player4));


		fix(player3,player4);

		System.out.println();
		System.out.println("the diffrence after fix of max min: "+diffrence2);
		System.out.println("player3 numbers after distributing"+Arrays.toString(player3));
		System.out.println("player4 numbers after distributing"+Arrays.toString(player4));

		//end max to player1,min to player2
		System.out.println();
		System.out.println("answer:");


		//print the best situation
		printTheBestSolution(player1,player2,player3,player4);
	}

}
