package AV2;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class QueueServer extends CommonServer implements Server {
	public QueueServer(int port) {
		try {
			this.setIp(Inet4Address.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setPort(port);
		this.setStop(false);
		}
	public void start(ClientHandler clienthandler) {
		runServer(clienthandler);
	}
	private void startServer(ClientHandler clientHandler) throws IOException {
		ServerSocket theServer = new ServerSocket(this.getPort());
		theServer.setSoTimeout(99999);
		while (!this.getStop()) {
			try {
				System.out.println("Server Running");
				Socket aClient = theServer.accept();
				try {
					clientHandler.handle(aClient.getInputStream(), aClient.getOutputStream());
					aClient.close();
				} catch (StopServerException e) {
					// TODO Auto-generated catch block
					this.setStop(true);
				}
			} catch (SocketTimeoutException e) {
				System.out.println(e.getMessage() + " ~~SocketTimeoutException");
			} catch (IOException e) {
				System.out.println(e.getMessage() + " ~~IOException");
			}
		}
		theServer.close();
		this.setStop(true);
	}

	@Override
	public void runServer(ClientHandler clientHandler) {
		new Thread(()->{
			try {
				startServer(clientHandler);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
	}
	@Override
	public void stop() {
		this.setStop(true);

	}

}
