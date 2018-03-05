package AV2;

public class Mainpipe {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server server=new QueueServer(6400);
		ClientHandler cli = new CLiHandler();
		server.start(cli);
	}

}
