public class DeviceRegistry {
    private final java.util.List<SmartClassroomDevice> devices = new java.util.ArrayList<>();

    public void add(SmartClassroomDevice d) { devices.add(d); }

    private SmartClassroomDevice findByName(String simpleName) {
        for (SmartClassroomDevice d : devices) {
            if (d.getClass().getSimpleName().equals(simpleName)) {
                return d;
            }
        }
        throw new IllegalStateException("Missing: " + simpleName);
    }

    public PowerDevice getPowerDevice(String simpleName) {
        SmartClassroomDevice d = findByName(simpleName);
        if (d instanceof PowerDevice) return (PowerDevice) d;
        throw new IllegalStateException(simpleName + " does not support power control");
    }

    public BrightnessDevice getBrightnessDevice(String simpleName) {
        SmartClassroomDevice d = findByName(simpleName);
        if (d instanceof BrightnessDevice) return (BrightnessDevice) d;
        throw new IllegalStateException(simpleName + " does not support brightness control");
    }

    public TemperatureDevice getTemperatureDevice(String simpleName) {
        SmartClassroomDevice d = findByName(simpleName);
        if (d instanceof TemperatureDevice) return (TemperatureDevice) d;
        throw new IllegalStateException(simpleName + " does not support temperature control");
    }

    public AttendanceDevice getAttendanceDevice(String simpleName) {
        SmartClassroomDevice d = findByName(simpleName);
        if (d instanceof AttendanceDevice) return (AttendanceDevice) d;
        throw new IllegalStateException(simpleName + " does not support attendance scanning");
    }

    public InputSourceDevice getInputSourceDevice(String simpleName) {
        SmartClassroomDevice d = findByName(simpleName);
        if (d instanceof InputSourceDevice) return (InputSourceDevice) d;
        throw new IllegalStateException(simpleName + " does not support input source connection");
    }
}
