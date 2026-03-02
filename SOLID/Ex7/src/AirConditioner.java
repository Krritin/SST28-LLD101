public class AirConditioner implements SmartClassroomDevice, PowerDevice, TemperatureDevice {

    @Override
    public void powerOn() {
        // no output needed
    }

    @Override
    public void powerOff() {
        System.out.println("AC OFF");
    }

    @Override
    public void setTemperatureC(int c) {
        System.out.println("AC set to " + c + "C");
    }
}
