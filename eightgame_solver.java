import java.util.*;

class nodeheur implements Comparable<nodeheur>
{
	String estado;
	int manhatan;
	String jf=eightgame_solver.jf;
	int profundidade;
	nodeheur(String estado, int profundidade)
	{
		this.estado=estado;
		manhatan=manhatan(estado, profundidade);
		this.profundidade=profundidade;
	}
	public int manhatan(String estado,int profundidade)
	{	

		int indexfinal=0;
		int nrdeslocacoes=0;
		int dx=0;
		int dy=0;
		int fx=0;
		int fy=0;
	    for (int x = 0; x < 9; x++){ 
	            char jogada = estado.charAt(x); 
	            if(x<3)
				{
					dx=x;
					dy=0;
				}
				if(x>2 && x<6)
				{
					dx=x%3;
					dy=1;
				}
				if(x>5)
				{
					dx=x%3;
					dy=2;
				}

	            if (jogada!= '0') {
	                l:for(int i=0;i<9;i++){
						if(jf.charAt(i)==jogada){
							indexfinal=i;
							break l;}

					} 
					if(indexfinal<3)
					{
						fx=indexfinal;
						fy=0;
					}
					if(indexfinal>2 && indexfinal<6)
					{
						fx=indexfinal%3;
						fy=1;
					}
					if(indexfinal>5)
					{
						fx=indexfinal%3;
						fy=2;
					}

	                dx = dx-fx; 
	            	dy = dy-fy;
	                nrdeslocacoes += Math.abs(dx) + Math.abs(dy); 
	            } 
	        }


		return nrdeslocacoes + profundidade;
	}
	@Override
	public int compareTo(nodeheur a){
	 
	    if(this.manhatan > a.manhatan){
	        return 1;
	    }
	    return -1;
	}

}

class node{
	int prof;
	String cam;
	node(int p, String c)
	{
		prof=p;
		cam=cam+c;
	}
}
class eightgame_solver
	{

	static int ai[]=new int[9];
	static int af[]=new int[9];
	static int zp;
	static Map<String,node> map = new HashMap<String, node>();
	static LinkedList<String> fila=new LinkedList<String>();
	static LinkedList<String> desc=new LinkedList<String>();
	static long start, end;
	static String jf;
	static PriorityQueue<nodeheur> pqueue = new PriorityQueue<nodeheur>();

	//---------------------------------------    A*/Greedy   --------------------------------------------------------
	public static void aestrela()
	{
		String a=Arrays.toString(ai).replaceAll("\\[|\\]|,|\\s", "");
		pqueue.add(new nodeheur(a,0));
		node eheh=new node(0,"");
		map.put(a,eheh);
		while(!pqueue.isEmpty())
			{	
				nodeheur yes = pqueue.poll();
				a=yes.estado;
				desc_heur(a, "a*");
			}

	}

	public static void greedy()
	{
		String a=Arrays.toString(ai).replaceAll("\\[|\\]|,|\\s", "");
		pqueue.add(new nodeheur(a,0));
		node eheh=new node(0,"");
		map.put(a,eheh);
		while(!pqueue.isEmpty())
			{	
				nodeheur yes = pqueue.poll();
				a=yes.estado;
				desc_heur(a, "greedy");
			}

	}

	public static void desc_heur(String b, String alg)
	{
			String a=b;
			int zer=0;
			int t=b.length();
			l:for(int i=0;i<t;i++){
				if(b.charAt(i)=='0'){
					zer=i;
					break l;}

			}
			char[] c=a.toCharArray();

			//up
			if(zer>2)
			{
				char temp=c[zer];
				c[zer]=c[zer-3];
				c[zer-3]=temp;
				a=new String(c);
				if(!map.containsKey(a)){
		    		map.put(a,new node((map.get(b).prof+1),(map.get(b).cam+"U")));
					if(a.equals(jf))
					{
						end = System.currentTimeMillis();
						System.out.println("Tempo de execução: "+(end-start)+" ms.");
						if(alg=="a*")
							System.out.println("A* profundidade: "+map.get(a).prof);
						if(alg=="greedy")
							System.out.println("Gredy profundidade: "+map.get(a).prof);
						System.out.println("Caminho percorrido: "+map.get(a).cam.replaceAll("null", ""));
						System.exit(0);
					}
					if(alg=="a*")
						pqueue.add(new nodeheur(a,map.get(a).prof));
					if(alg=="greedy")
						pqueue.add(new nodeheur(a,0));
				}
			
		    	
			}
			
			a=b;
			c=a.toCharArray();
			//down
			if(zer<6)
			{
				char temp=c[zer];
				c[zer]=c[zer+3];
				c[zer+3]=temp;
				a=new String(c);
				if(!map.containsKey(a)){
		    		map.put(a,new node((map.get(b).prof+1),(map.get(b).cam+"D")));
		   			if(a.equals(jf))
					{
						end = System.currentTimeMillis();
						System.out.println("Tempo de execução: "+(end-start)+" ms.");
						if(alg=="a*")
							System.out.println("A* profundidade: "+map.get(a).prof);
						if(alg=="greedy")
							System.out.println("Gredy profundidade: "+map.get(a).prof);
						System.out.println("Caminho percorrido: "+map.get(a).cam.replaceAll("null", ""));
						System.exit(0);
					}
					if(alg=="a*")
						pqueue.add(new nodeheur(a,map.get(a).prof));
					if(alg=="greedy")
						pqueue.add(new nodeheur(a,0));
				
		    	}
			}
			
			a=b;
			c=a.toCharArray();
			//right
			if(zer!=2 && zer!=5 && zer!=8)
			{
				char temp=c[zer];
				c[zer]=c[zer+1];
				c[zer+1]=temp;
				a=new String(c);
				if(!map.containsKey(a)){
		    		map.put(a,new node((map.get(b).prof+1),(map.get(b).cam+"R")));		    		
					if(a.equals(jf))
					{
						end = System.currentTimeMillis();
						System.out.println("Tempo de execução: "+(end-start)+" ms.");
						if(alg=="a*")
							System.out.println("A* profundidade: "+map.get(a).prof);
						if(alg=="greedy")
							System.out.println("Gredy profundidade: "+map.get(a).prof);
						System.out.println("Caminho percorrido: "+map.get(a).cam.replaceAll("null", ""));
						System.exit(0);
					}
					if(alg=="a*")
						pqueue.add(new nodeheur(a,map.get(a).prof));
					if(alg=="greedy")
						pqueue.add(new nodeheur(a,0));
				}
			}
			a=b;
			c=a.toCharArray();
			//left
			if(zer!=0 && zer!=3 && zer!=6)
			{
				char temp=c[zer];
				c[zer]=c[zer-1];
				c[zer-1]=temp;
				a=new String(c);
				if(!map.containsKey(a)){
		    		map.put(a,new node((map.get(b).prof+1),(map.get(b).cam+"L")));
		  			if(a.equals(jf))
					{
						end = System.currentTimeMillis();
						System.out.println("Tempo de execução: "+(end-start)+" ms.");
						if(alg=="a*")
							System.out.println("A* profundidade: "+map.get(a).prof);
						if(alg=="greedy")
							System.out.println("Gredy profundidade: "+map.get(a).prof);
						System.out.println("Caminho percorrido: "+map.get(a).cam.replaceAll("null", ""));
						System.exit(0);
					}
					if(alg=="a*")
						pqueue.add(new nodeheur(a,map.get(a).prof));
					if(alg=="greedy")
						pqueue.add(new nodeheur(a,0));
				
				
				}
			}
		
		return;

	}

	//---------------------------------------  IDFS   --------------------------------------------------------
	public static void idfs(int max)
	{
		for(int i=0;i<max;i++){
			map.clear();
			fila.clear();
			String a=Arrays.toString(ai).replaceAll("\\[|\\]|,|\\s", "");
			fila.add(a);
			node eheh=new node(0,"");
			map.put(a,eheh);
			while(!fila.isEmpty())
			{	
				a=fila.remove();
				if((map.get(a).prof+1)<=i)
					desc_idfs(a);
			}
			
		
		}
		return;
	}

	public static void desc_idfs(String b)
	{

			String a=b;
			int zer=0;
			int t=b.length();
			l:for(int i=0;i<t;i++){
				if(b.charAt(i)=='0'){
					zer=i;
					break l;}

			}
			char[] c=a.toCharArray();

			//up
			if(zer>2)
			{
				char temp=c[zer];
				c[zer]=c[zer-3];
				c[zer-3]=temp;
				a=new String(c);
				if(!map.containsKey(a)){
		    		map.put(a,new node((map.get(b).prof+1),(map.get(b).cam+"U")));
					if(a.equals(jf))
					{
						end = System.currentTimeMillis();
						System.out.println("Tempo de execução: "+(end-start)+" ms.");
						System.out.println("IDFS profundidade: "+map.get(a).prof);
						System.out.println("Caminho percorrido: "+map.get(a).cam.replaceAll("null", ""));
						System.exit(0);
					}
					fila.addLast(a);
				}
			
		    	
			}
			
			a=b;
			c=a.toCharArray();
			//down
			if(zer<6)
			{
				char temp=c[zer];
				c[zer]=c[zer+3];
				c[zer+3]=temp;
				a=new String(c);
				if(!map.containsKey(a)){
		    		map.put(a,new node((map.get(b).prof+1),(map.get(b).cam+"D")));
					if(a.equals(jf))
					{
						end = System.currentTimeMillis();
						System.out.println("Tempo de execução: "+(end-start)+" ms.");
						System.out.println("IDFS profundidade: "+map.get(a).prof);
						System.out.println("Caminho percorrido: "+map.get(a).cam.replaceAll("null", ""));
						System.exit(0);
					}
					fila.addLast(a);
				
		    	}
			}
			
			a=b;
			c=a.toCharArray();
			//right
			if(zer!=2 && zer!=5 && zer!=8)
			{
				char temp=c[zer];
				c[zer]=c[zer+1];
				c[zer+1]=temp;
				a=new String(c);
				if(!map.containsKey(a)){
		    		map.put(a,new node((map.get(b).prof+1),(map.get(b).cam+"R")));
					if(a.equals(jf))
					{
						end = System.currentTimeMillis();
						System.out.println("Tempo de execução: "+(end-start)+" ms.");
						System.out.println("IDFS profundidade: "+map.get(a).prof);
						System.out.println("Caminho percorrido: "+map.get(a).cam.replaceAll("null", ""));
						System.exit(0);
					}
					fila.addLast(a);
				}
			}
			a=b;
			c=a.toCharArray();
			//left
			if(zer!=0 && zer!=3 && zer!=6)
			{
				char temp=c[zer];
				c[zer]=c[zer-1];
				c[zer-1]=temp;
				a=new String(c);
				if(!map.containsKey(a)){
		    		map.put(a,new node((map.get(b).prof+1),(map.get(b).cam+"L")));
					if(a.equals(jf))
					{
						end = System.currentTimeMillis();
						System.out.println("Tempo de execução: "+(end-start)+" ms.");
						System.out.println("IDFS profundidade: "+map.get(a).prof);
						System.out.println("Caminho percorrido: "+map.get(a).cam.replaceAll("null", ""));
						System.exit(0);
					}
					fila.addLast(a);
				
				
				}
			}
		
		return;

	}


	//---------------------------------------  BFS/DFS  --------------------------------------------------------



	public static void insert()
	{
		while(!desc.isEmpty())
		{

				String a=desc.remove();
				fila.addFirst(a);
		}

	}
	public static void search(String func)
	{
		
		String a=Arrays.toString(ai).replaceAll("\\[|\\]|,|\\s", "");
		fila.add(a);
		node eheh=new node(0,"");
		map.put(a,eheh);
		while(!fila.isEmpty())
		{
			a=fila.remove();
			desc(a,func);
		}

		return ;
	}

	public static void desc(String b, String alg)
	{
		String a=b;
		int zer=0;
		int t=b.length();
		l:for(int i=0;i<t;i++){
			if(b.charAt(i)=='0'){
				zer=i;
				break l;}

		}
		char[] c=a.toCharArray();

		//up
		if(zer>2)
		{
			char temp=c[zer];
			c[zer]=c[zer-3];
			c[zer-3]=temp;
			a=new String(c);
			if(!map.containsKey(a)){
	    		map.put(a,new node((map.get(b).prof+1),(map.get(b).cam+"U")));
	    		
	    	

			if(alg=="bfs"){
				if(a.equals(jf))
				{
					end = System.currentTimeMillis();
					System.out.println("Tempo de execução: "+(end-start)+" ms.");
					System.out.println("BFS profundidade: "+map.get(a).prof);
					System.out.println("Caminho percorrido: "+map.get(a).cam.replaceAll("null", ""));
					System.exit(0);
				}
				fila.addLast(a);
			}
			else if(alg=="dfs"){
				if(a.equals(jf))
				{
					end = System.currentTimeMillis();
					System.out.println("Tempo de execução: "+(end-start)+" ms.");
					System.out.println("DFS profundidade: "+map.get(a).prof);
					System.out.println("Caminho percorrido: "+map.get(a).cam.replaceAll("null", ""));
					System.exit(0);
				}
				desc.addFirst(a);

			}
		
	    	}
	}
		
		a=b;
		c=a.toCharArray();
		//down
		if(zer<6)
		{
			char temp=c[zer];
			c[zer]=c[zer+3];
			c[zer+3]=temp;
			a=new String(c);
			if(!map.containsKey(a)){
	    		map.put(a,new node((map.get(b).prof+1),(map.get(b).cam+"D")));
	    		
	    		if(alg=="bfs"){
				//a=desc.remove();
				if(a.equals(jf))
				{
					end = System.currentTimeMillis();
					System.out.println("Tempo de execução: "+(end-start)+" ms.");
					System.out.println("BFS profundidade: "+map.get(a).prof);
					System.out.println("Caminho percorrido: "+map.get(a).cam.replaceAll("null", ""));
					System.exit(0);
				}
				fila.addLast(a);
			}
			else if(alg=="dfs"){
				if(a.equals(jf))
				{
					end = System.currentTimeMillis();
					System.out.println("Tempo de execução: "+(end-start)+" ms.");
					System.out.println("DFS profundidade: "+map.get(a).prof);
					System.out.println("Caminho percorrido: "+map.get(a).cam.replaceAll("null", ""));
					System.exit(0);
				}
				desc.addFirst(a);

			}
	    	}
		}
		a=b;
		c=a.toCharArray();
		//left
		if(zer!=0 && zer!=3 && zer!=6)
		{
			char temp=c[zer];
			c[zer]=c[zer-1];
			c[zer-1]=temp;
			a=new String(c);
			if(!map.containsKey(a)){
	    		map.put(a,new node((map.get(b).prof+1),(map.get(b).cam+"L")));
	    		if(alg=="bfs"){
					if(a.equals(jf))
					{
						end = System.currentTimeMillis();
						System.out.println("Tempo de execução: "+(end-start)+" ms.");
						System.out.println("BFS profundidade: "+map.get(a).prof);
						System.out.println("Caminho percorrido: "+map.get(a).cam.replaceAll("null", ""));
						System.exit(0);
					}
				fila.addLast(a);
			}
			else if(alg=="dfs"){
				if(a.equals(jf))
				{
					end = System.currentTimeMillis();
					System.out.println("Tempo de execução: "+(end-start)+" ms.");
					System.out.println("DFS profundidade: "+map.get(a).prof);
					System.out.println("Caminho percorrido: "+map.get(a).cam.replaceAll("null", ""));
					System.exit(0);
				}
				desc.addFirst(a);

			}
	    	}
		}
		a=b;
		c=a.toCharArray();
		//right
		if(zer!=2 && zer!=5 && zer!=8)
		{
			char temp=c[zer];
			c[zer]=c[zer+1];
			c[zer+1]=temp;
			a=new String(c);
			if(!map.containsKey(a)){
	    		map.put(a,new node((map.get(b).prof+1),(map.get(b).cam+"R")));
	    		if(alg=="bfs"){
					if(a.equals(jf))
					{
						end = System.currentTimeMillis();
						System.out.println("Tempo de execução: "+(end-start)+" ms.");
						System.out.println("BFS profundidade: "+map.get(a).prof);
						System.out.println("Caminho percorrido: "+map.get(a).cam.replaceAll("null", ""));
						System.exit(0);
					}
					fila.addLast(a);
			}
			else if(alg=="dfs"){
				if(a.equals(jf))
				{
					end = System.currentTimeMillis();
					System.out.println("Tempo de execução: "+(end-start)+" ms.");
					System.out.println("DFS profundidade: "+map.get(a).prof);
					System.out.println("Caminho percorrido: "+map.get(a).cam.replaceAll("null", ""));
					System.exit(0);
				}
				desc.addFirst(a);

			}
	    	}
		}
		if(alg=="dfs")
			insert();
		return;
	}

	//---------------------------------------  Input --------------------------------------------------------



	public static void main(String args[])
	{
		System.out.println("Insira a configurção inicial");
		Scanner in=new Scanner(System.in);
		int inv=0;
		int invf=0;
		int soli[]=new int[8];
		int solf[]=new int[8];
		int k=0;
		for(int i=0;i<9;i++){
			ai[i]=in.nextInt();
			if(ai[i]==0)
				zp=i;
			else
			 {
			 	soli[k]=ai[i];
			 	k++;
			 }

		}
		System.out.println("Insira a configurção final");
		k=0;
		for(int i=0;i<9;i++){
			af[i]=in.nextInt();
			if(af[i]!=0)
			{
				solf[k]=af[i];
				k++;
			}
		}
		for (int i = 0; i < 8; i++){
		    for (int j = i+1; j < 8; j++){
			if (soli[i] > soli[j] )
			    inv++;
		    }
		}
		for (int i = 0; i < 8; i++){
		    for (int j = i+1; j < 8; j++){
			if (solf[i] > solf[j])
			    invf++;
		    }
		}
		jf=Arrays.toString(af).replaceAll("\\[|\\]|,|\\s", "");
		if((inv % 2 == 0 && invf % 2==0) || (inv % 2 == 1 && invf % 2==1))
		{
		    System.out.println("Escolha um algoritmo: ");
		    System.out.printf("-----------------------------------\n1-BFS\n2-DFS\n3-IDFS\n4-A*\n5-Greedy\n-----------------------------------\n");
			int opt=in.nextInt();
			
			start = System.currentTimeMillis();
			if(opt==1)
				search("bfs");
			if(opt==2)
				search("dfs");
			if(opt==3)
				idfs(50);
			if(opt==4)
				aestrela();
			if(opt==5)
				greedy();
			end = System.currentTimeMillis();
			System.out.println("Tempo de execução: "+(end-start)+" ms.");
		}
		else
		    System.out.println("Não é possível chegar da configuração inicial á configuração final");
	    }
}



