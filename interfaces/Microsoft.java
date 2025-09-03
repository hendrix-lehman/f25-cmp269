class Microsoft extends Computer {

  public static final String OS = "Windows";

    public Microsoft(String model) {
        super("Microsoft", model);
    // this.OS = "abc"; // This line will cause a compilation error because OS is final
    }

    @Override
    public void assemble() {
        System.out.println("Assembling Microsoft computer");
    }

    @Override
  public void start() {
    System.out.printf("Starting the %s computer model %s...\n", brand, model);
  }

  public static void developerDeveloperDeveloper() {
    System.out.println("Developer! Developer! Developer!");
  }
}
