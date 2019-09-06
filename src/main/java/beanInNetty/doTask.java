package beanInNetty;

public  class doTask extends Thread {
	private int name;
	public doTask(int name){
		this.name=name;
	}
	public void run() {
		try {
			new Client(name).connect("127.0.0.1", 8888);
		} catch (Exception e) {				
			e.printStackTrace();
		}
	}
}	