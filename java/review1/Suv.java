// add package name here if needed
//
class Suv extends Automobile {

  Suv(String make, String model, int year) {
    super(make, model, year, 4, "V6", 6, 7, 4, 6, "large", "large");
  }

  @Override
  public void honkHorn() {
    System.out.println("SUV horn honk: Honk Honk!");
  }

  @Override
  public String toString() {
    return "SUV [doors=" + super.getDoors() + ", engine=" + super.getEngine() + ", windows=" + super.getWindows() + ", seats=" + super.getSeats() + ", wheels="
        + super.getWheels() + ", windshield=" + super.WINDSHIELD + ", rearviewMirror=" + super.REARVIEW_MIRROR + ", sideMirrors="
        + super.SIDE_MIRRORS + ", lights=" + super.getLights() + ", trunk=" + super.getTrunkSize() + ", hood=" + super.getHoodSize() + "]";
  }
}

