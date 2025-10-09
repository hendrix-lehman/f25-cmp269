class Sedan extends Automobile {
  
  Sedan(String make, String model, int year) {
    super(make, model, year, 4, "V4", 4, 5, 4, 4, "small", "small");
  }

  @Override
  public void honkHorn() {
    System.out.println("Sedan horn honk: Beep Beep!");
  }

  @Override
  public String toString() {
    return "Sedan [doors=" + super.getDoors() + ", engine=" + super.getEngine() + ", windows=" + super.getWindows() + ", seats=" + super.getSeats() + ", wheels="
        + super.getWheels() + ", windshield=" + super.WINDSHIELD + ", rearviewMirror=" + super.REARVIEW_MIRROR + ", sideMirrors="
        + super.SIDE_MIRRORS + ", lights=" + super.getLights() + ", trunk=" + super.getTrunkSize() + ", hood=" + super.getHoodSize() + "]";
  }

}

