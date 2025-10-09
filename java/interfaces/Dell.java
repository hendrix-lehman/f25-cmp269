class Dell extends Computer {

    public Dell(String model) {
        super("Dell", model);
    }

    @Override
    public void assemble() {
        System.out.println("Assembling Dell computer");
    }
}
