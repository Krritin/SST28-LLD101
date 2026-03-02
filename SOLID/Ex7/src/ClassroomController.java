public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    public void startClass() {
        PowerDevice pjPower = reg.getPowerDevice("Projector");
        InputSourceDevice pjInput = reg.getInputSourceDevice("Projector");
        pjPower.powerOn();
        pjInput.connectInput("HDMI-1");

        BrightnessDevice lights = reg.getBrightnessDevice("LightsPanel");
        lights.setBrightness(60);

        TemperatureDevice ac = reg.getTemperatureDevice("AirConditioner");
        ac.setTemperatureC(24);

        AttendanceDevice scan = reg.getAttendanceDevice("AttendanceScanner");
        System.out.println("Attendance scanned: present=" + scan.scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        reg.getPowerDevice("Projector").powerOff();
        reg.getPowerDevice("LightsPanel").powerOff();
        reg.getPowerDevice("AirConditioner").powerOff();
    }
}
