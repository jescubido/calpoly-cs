package AbstractFactoryPattern;

public class TestAbstractFactory {
    public static void main(String[] args) {
		testAbstractFactory();
	}

	private static void testAbstractFactory() {
		System.out.println("---------------------------------");
		Computer pc = AbstractFactoryPattern.ComputerFactory.getComputer(new PCFactory("2 GB","500 GB","2.4 GHz"));
		Computer server = AbstractFactoryPattern.ComputerFactory.getComputer(new ServerFactory("16 GB","1 TB","2.9 GHz"));
		System.out.println("AbstractFactory PC Configuration: "+pc);
		System.out.println("AbstractFactory Server Configuration: "+server);
	}
}
