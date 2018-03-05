package AV2;

public interface Server {
	public void start(ClientHandler clienthandler);
	public void runServer(ClientHandler clienthandler);
	public void stop();
	
	
	
}
