package virtual_robot.controller;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.BNO055IMUImpl;
import com.qualcomm.robotcore.hardware.*;
import javafx.scene.transform.Rotate;

import javax.naming.directory.SearchResult;

public class CyberPunk extends VirtualBot {

    private MotorType motorType;

//    private DcMotorImpl stangaFata = null;
//    private DcMotorImpl stangaSpate = null;
//    private DcMotorImpl dreaptaFata = null;
//    private DcMotorImpl dreaptaSpate = null;
    private DcMotorImpl[] motors = null;
    private DcMotorImpl ridicareBrat = null;
    private DcMotorImpl scripeteSlide = null;
    private ServoImpl gheara = null;
    private BNO055IMUImpl imu = null;

    private double wheelCircumference;
    private double interWheelWidth;
    private double interWheelLength;
    private double wlAverage;

    private double[][] tWR; //Transform from wheel motion to robot motion


    public CyberPunk(VirtualRobotController controller, String fxmlResourceName, int robotIndex) {
        super(controller, fxmlResourceName, robotIndex);

        motors = new DcMotorImpl[] {
                (DcMotorImpl) hardwareMap.get(DcMotor.class, "stangaSpate"),
                (DcMotorImpl) hardwareMap.get(DcMotor.class, "stangaFata"),
                (DcMotorImpl) hardwareMap.get(DcMotor.class, "dreaptaFata"),
                (DcMotorImpl) hardwareMap.get(DcMotor.class, "dreaptaSpate")
        };
        ridicareBrat = (DcMotorImpl) hardwareMap.get(DcMotor.class, "ridicareBrat");
        scripeteSlide = (DcMotorImpl) hardwareMap.get(DcMotor.class, "scripeteSlide");
        gheara = (ServoImpl) hardwareMap.get(Servo.class, "gheara");
        imu = hardwareMap.get(BNO055IMUImpl.class, "imu");

        wheelCircumference = Math.PI * botWidth / 4.5;
        interWheelWidth = botWidth * 8.0 / 9.0;
        interWheelLength = botWidth * 7.0 / 9.0;
        wlAverage = (interWheelLength + interWheelWidth) / 2.0;

        tWR = new double[][] {
                {-0.25, 0.25, -0.25, 0.25},
                {0.25, 0.25, 0.25, 0.25},
                {-0.25/ wlAverage, -0.25/ wlAverage, 0.25/ wlAverage, 0.25/ wlAverage},
                {-0.25, 0.25, 0.25, -0.25}
        };
    }

    @Override
    public void updateStateAndSensors(double millis) {
        double[] deltaPos = new double[4];
        double[] w = new double[4];

        for (int i = 0; i < 4; i++) {
            deltaPos[i] = motors[i].update(millis);
            w[i] = deltaPos[i] * wheelCircumference / motorType.TICKS_PER_ROTATION;
            if (i < 2) w[i] = -w[i];
        }

        double[] robotDeltaPos = new double[] {0,0,0,0};
        for (int i=0; i<4; i++){
            for (int j = 0; j<4; j++){
                robotDeltaPos[i] += tWR[i][j] * w[j];
            }
        }

        double dxR = robotDeltaPos[0];
        double dyR = robotDeltaPos[1];
        double headingChange = robotDeltaPos[2];
        double avgHeading = headingRadians + headingChange / 2.0;

        double sin = Math.sin(avgHeading);
        double cos = Math.cos(avgHeading);

        x += dxR * cos - dyR * sin;
        y += dxR * sin + dyR * cos;
        headingRadians += headingChange;

        if (x >  (halfFieldWidth - halfBotWidth)) x = halfFieldWidth - halfBotWidth;
        else if (x < (halfBotWidth - halfFieldWidth)) x = halfBotWidth - halfFieldWidth;
        if (y > (halfFieldWidth - halfBotWidth)) y = halfFieldWidth - halfBotWidth;
        else if (y < (halfBotWidth - halfFieldWidth)) y = halfBotWidth - halfFieldWidth;

        if (headingRadians > Math.PI) headingRadians -= 2.0 * Math.PI;
        else if (headingRadians < -Math.PI) headingRadians += 2.0 * Math.PI;
        //gyro.updateHeading(headingRadians * 180.0 / Math.PI);
        imu.updateHeadingRadians(headingRadians);
    }

    @Override
    public void powerDownAndReset() {
        for (int i=0; i<4; i++) motors[i].stopAndReset();
        ridicareBrat.stopAndReset();
        scripeteSlide.stopAndReset();
        //gyro.deinit();
        imu.close();
    }

    @Override
    protected void createHardwareMap() {
        motorType = MotorType.Neverest40;

        hardwareMap = new HardwareMap();
        hardwareMap.put("stangaFata", new DcMotorImpl(motorType));
        hardwareMap.put("dreaptaFata", new DcMotorImpl(motorType));
        hardwareMap.put("stangaSpate", new DcMotorImpl(motorType));
        hardwareMap.put("dreaptaSpate", new DcMotorImpl(motorType));
        hardwareMap.put("ridicareBrat", new DcMotorImpl(motorType));
        hardwareMap.put("scripeteSlide", new DcMotorImpl(motorType));
        hardwareMap.put("gheara", new ServoImpl());
        hardwareMap.put("imu", new BNO055IMUImpl(this, 175));
    }

    public synchronized void updateDisplay(){
        super.updateDisplay();
//        ((Rotate)backServoArm.getTransforms().get(0)).setAngle(-180.0 * servo.getPosition());
//        if (gheara.getPosition() == 1) {
//            cube.setWidth(37.5);
//            cube.setHeight(20);
//        }
//        else {
//            cube.setHeight(0);
//            cube.setWidth(0);
//        }
    }
}
