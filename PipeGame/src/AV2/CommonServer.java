package AV2;

public abstract class CommonServer implements Server {
	public ClientHandler getClienthandler() {
		return clienthandler;
	}

	public void setClienthandler(ClientHandler clienthandler) {
		this.clienthandler = clienthandler;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setStop(Boolean stop) {
		this.stop=stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}
	public Boolean getStop() {
		return stop;
	}
	private ClientHandler clienthandler;
	private int port;
	private String ip;
	private volatile boolean stop;
	public abstract void start(ClientHandler clienthandler);

	public abstract void runServer(ClientHandler clienthandler);

	public abstract void stop() ;
}
