public class PhoneLauncher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PhoneLauncher launcher = new PhoneLauncher();
		launcher.launch();
	}

	public void launch(){
		SmartPhone sp = new SmartPhone("Motorola");
		System.out.println("This phone was made by: " + sp.getBrand());
		sp.playGame("Tetris");
		sp.call("18479317428");
		sp.call("12249177428");
		sp.call("12246226076");
		sp.call("18479176076");
		sp.call("007557261809");
		sp.printLastNumbers();
	}
}
