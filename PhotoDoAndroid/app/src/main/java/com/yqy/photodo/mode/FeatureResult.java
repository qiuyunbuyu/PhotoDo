package com.yqy.photodo.mode;

/**
 * Created by yqy on 2019/11/14.
 */

public class FeatureResult {

    /**
     * image_reset : NTyDKpmLM7RklVcRyv2xPA==
     * request_id : 1528687092,efbe87f7-6c0f-4754-b108-afe8f42abe17
     * time_used : 666
     * face_rectangle : {"top":1,"left":1,"wilewdth":1,"height":1}
     * result : {"three_parts":{"parts_ratio":"0.33:0.34:0.33","one_part":{"faceup_length":73.33,"faceup_ratio":0.33,"faceup_result":"faceup_normal"},"two_part":{"facemid_length":74.12,"facemid_ratio":0.34,"facemid_result":"facemid_normal"},"three_part":{"facedown_length":72.42,"facedown_ratio":0.33,"facedown_result":"facedown_long"}},"five_eyes":{"eyes_ratio":"0.80:1:1.41:1:0.80","one_eye":{"righteye_empty_length":24.21,"righteye_empty_ratio":0.8,"righteye_empty_result":"righteye_empty_normal"},"righteye":25.42,"three_eye":{"eyein_length":24.31,"eyein_ratio":1.41,"eyein_result":"eyein_long"},"lefteye":25.46,"five_eye":{"lefteye_empty_length":24.23,"lefteye_empty_ratio":0.8,"lefteye_empty_result":"lefteye_empty_short"}},"golden_triangle":62.14,"face":{"tempus_length":112.15,"zygoma_length":135.36,"face_length":219.41,"mandible_length":104.11,"E":116,"ABD_ratio":"1.31:1:1.45","face_type":"pointed_face"},"jaw":{"jaw_width":29.61,"jaw_length":15.03,"jaw_type":"sharp_jaw"},"eyebrow":{"brow_width":33.12,"brow_height":5.17,"brow_uptrend_angle":21.04,"brow_camber_angle":8.76,"brow_thick":3.17,"eyebrow_type":"bushy_eyebrows"},"eyes":{"eye_width":20.35,"eye_height":8.66,"angulus_oculi_medialis":58.32,"eyes_type":"big_eyes"},"nose":{"nose_width":28.12,"nose_type":"thick_nose"},"mouth":{"mouth_height":11.37,"mouth_width":42.34,"lip_thickness":6.8,"angulus_oris":85.67,"mouth_type":"thin_lip"}}
     */
    private String image_id;



    private String image_reset;
    private String request_id;
    private int time_used;
    private FaceRectangleBean face_rectangle;
    private ResultBean result;
    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }
    public String getImage_reset() {
        return image_reset;
    }

    public void setImage_reset(String image_reset) {
        this.image_reset = image_reset;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public int getTime_used() {
        return time_used;
    }

    public void setTime_used(int time_used) {
        this.time_used = time_used;
    }

    public FaceRectangleBean getFace_rectangle() {
        return face_rectangle;
    }

    public void setFace_rectangle(FaceRectangleBean face_rectangle) {
        this.face_rectangle = face_rectangle;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class FaceRectangleBean {
        /**
         * top : 1
         * left : 1
         * wilewdth : 1
         * height : 1
         */

        private int top;
        private int left;
        private int wilewdth;
        private int height;

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public int getWilewdth() {
            return wilewdth;
        }

        public void setWilewdth(int wilewdth) {
            this.wilewdth = wilewdth;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }

    public static class ResultBean {
        /**
         * three_parts : {"parts_ratio":"0.33:0.34:0.33","one_part":{"faceup_length":73.33,"faceup_ratio":0.33,"faceup_result":"faceup_normal"},"two_part":{"facemid_length":74.12,"facemid_ratio":0.34,"facemid_result":"facemid_normal"},"three_part":{"facedown_length":72.42,"facedown_ratio":0.33,"facedown_result":"facedown_long"}}
         * five_eyes : {"eyes_ratio":"0.80:1:1.41:1:0.80","one_eye":{"righteye_empty_length":24.21,"righteye_empty_ratio":0.8,"righteye_empty_result":"righteye_empty_normal"},"righteye":25.42,"three_eye":{"eyein_length":24.31,"eyein_ratio":1.41,"eyein_result":"eyein_long"},"lefteye":25.46,"five_eye":{"lefteye_empty_length":24.23,"lefteye_empty_ratio":0.8,"lefteye_empty_result":"lefteye_empty_short"}}
         * golden_triangle : 62.14
         * face : {"tempus_length":112.15,"zygoma_length":135.36,"face_length":219.41,"mandible_length":104.11,"E":116,"ABD_ratio":"1.31:1:1.45","face_type":"pointed_face"}
         * jaw : {"jaw_width":29.61,"jaw_length":15.03,"jaw_type":"sharp_jaw"}
         * eyebrow : {"brow_width":33.12,"brow_height":5.17,"brow_uptrend_angle":21.04,"brow_camber_angle":8.76,"brow_thick":3.17,"eyebrow_type":"bushy_eyebrows"}
         * eyes : {"eye_width":20.35,"eye_height":8.66,"angulus_oculi_medialis":58.32,"eyes_type":"big_eyes"}
         * nose : {"nose_width":28.12,"nose_type":"thick_nose"}
         * mouth : {"mouth_height":11.37,"mouth_width":42.34,"lip_thickness":6.8,"angulus_oris":85.67,"mouth_type":"thin_lip"}
         */

        private ThreePartsBean three_parts;
        private FiveEyesBean five_eyes;
        private double golden_triangle;
        private FaceBean face;
        private JawBean jaw;
        private EyebrowBean eyebrow;
        private EyesBean eyes;
        private NoseBean nose;
        private MouthBean mouth;

        public ThreePartsBean getThree_parts() {
            return three_parts;
        }

        public void setThree_parts(ThreePartsBean three_parts) {
            this.three_parts = three_parts;
        }

        public FiveEyesBean getFive_eyes() {
            return five_eyes;
        }

        public void setFive_eyes(FiveEyesBean five_eyes) {
            this.five_eyes = five_eyes;
        }

        public double getGolden_triangle() {
            return golden_triangle;
        }

        public void setGolden_triangle(double golden_triangle) {
            this.golden_triangle = golden_triangle;
        }

        public FaceBean getFace() {
            return face;
        }

        public void setFace(FaceBean face) {
            this.face = face;
        }

        public JawBean getJaw() {
            return jaw;
        }

        public void setJaw(JawBean jaw) {
            this.jaw = jaw;
        }

        public EyebrowBean getEyebrow() {
            return eyebrow;
        }

        public void setEyebrow(EyebrowBean eyebrow) {
            this.eyebrow = eyebrow;
        }

        public EyesBean getEyes() {
            return eyes;
        }

        public void setEyes(EyesBean eyes) {
            this.eyes = eyes;
        }

        public NoseBean getNose() {
            return nose;
        }

        public void setNose(NoseBean nose) {
            this.nose = nose;
        }

        public MouthBean getMouth() {
            return mouth;
        }

        public void setMouth(MouthBean mouth) {
            this.mouth = mouth;
        }

        public static class ThreePartsBean {
            /**
             * parts_ratio : 0.33:0.34:0.33
             * one_part : {"faceup_length":73.33,"faceup_ratio":0.33,"faceup_result":"faceup_normal"}
             * two_part : {"facemid_length":74.12,"facemid_ratio":0.34,"facemid_result":"facemid_normal"}
             * three_part : {"facedown_length":72.42,"facedown_ratio":0.33,"facedown_result":"facedown_long"}
             */

            private String parts_ratio;
            private OnePartBean one_part;
            private TwoPartBean two_part;
            private ThreePartBean three_part;

            public String getParts_ratio() {
                return parts_ratio;
            }

            public void setParts_ratio(String parts_ratio) {
                this.parts_ratio = parts_ratio;
            }

            public OnePartBean getOne_part() {
                return one_part;
            }

            public void setOne_part(OnePartBean one_part) {
                this.one_part = one_part;
            }

            public TwoPartBean getTwo_part() {
                return two_part;
            }

            public void setTwo_part(TwoPartBean two_part) {
                this.two_part = two_part;
            }

            public ThreePartBean getThree_part() {
                return three_part;
            }

            public void setThree_part(ThreePartBean three_part) {
                this.three_part = three_part;
            }

            public static class OnePartBean {
                /**
                 * faceup_length : 73.33
                 * faceup_ratio : 0.33
                 * faceup_result : faceup_normal
                 */

                private double faceup_length;
                private double faceup_ratio;
                private String faceup_result;

                public double getFaceup_length() {
                    return faceup_length;
                }

                public void setFaceup_length(double faceup_length) {
                    this.faceup_length = faceup_length;
                }

                public double getFaceup_ratio() {
                    return faceup_ratio;
                }

                public void setFaceup_ratio(double faceup_ratio) {
                    this.faceup_ratio = faceup_ratio;
                }

                public String getFaceup_result() {
                    return faceup_result;
                }

                public void setFaceup_result(String faceup_result) {
                    this.faceup_result = faceup_result;
                }
            }

            public static class TwoPartBean {
                /**
                 * facemid_length : 74.12
                 * facemid_ratio : 0.34
                 * facemid_result : facemid_normal
                 */

                private double facemid_length;
                private double facemid_ratio;
                private String facemid_result;

                public double getFacemid_length() {
                    return facemid_length;
                }

                public void setFacemid_length(double facemid_length) {
                    this.facemid_length = facemid_length;
                }

                public double getFacemid_ratio() {
                    return facemid_ratio;
                }

                public void setFacemid_ratio(double facemid_ratio) {
                    this.facemid_ratio = facemid_ratio;
                }

                public String getFacemid_result() {
                    return facemid_result;
                }

                public void setFacemid_result(String facemid_result) {
                    this.facemid_result = facemid_result;
                }
            }

            public static class ThreePartBean {
                /**
                 * facedown_length : 72.42
                 * facedown_ratio : 0.33
                 * facedown_result : facedown_long
                 */

                private double facedown_length;
                private double facedown_ratio;
                private String facedown_result;

                public double getFacedown_length() {
                    return facedown_length;
                }

                public void setFacedown_length(double facedown_length) {
                    this.facedown_length = facedown_length;
                }

                public double getFacedown_ratio() {
                    return facedown_ratio;
                }

                public void setFacedown_ratio(double facedown_ratio) {
                    this.facedown_ratio = facedown_ratio;
                }

                public String getFacedown_result() {
                    return facedown_result;
                }

                public void setFacedown_result(String facedown_result) {
                    this.facedown_result = facedown_result;
                }
            }
        }

        public static class FiveEyesBean {
            /**
             * eyes_ratio : 0.80:1:1.41:1:0.80
             * one_eye : {"righteye_empty_length":24.21,"righteye_empty_ratio":0.8,"righteye_empty_result":"righteye_empty_normal"}
             * righteye : 25.42
             * three_eye : {"eyein_length":24.31,"eyein_ratio":1.41,"eyein_result":"eyein_long"}
             * lefteye : 25.46
             * five_eye : {"lefteye_empty_length":24.23,"lefteye_empty_ratio":0.8,"lefteye_empty_result":"lefteye_empty_short"}
             */

            private String eyes_ratio;
            private OneEyeBean one_eye;
            private double righteye;
            private ThreeEyeBean three_eye;
            private double lefteye;
            private FiveEyeBean five_eye;

            public String getEyes_ratio() {
                return eyes_ratio;
            }

            public void setEyes_ratio(String eyes_ratio) {
                this.eyes_ratio = eyes_ratio;
            }

            public OneEyeBean getOne_eye() {
                return one_eye;
            }

            public void setOne_eye(OneEyeBean one_eye) {
                this.one_eye = one_eye;
            }

            public double getRighteye() {
                return righteye;
            }

            public void setRighteye(double righteye) {
                this.righteye = righteye;
            }

            public ThreeEyeBean getThree_eye() {
                return three_eye;
            }

            public void setThree_eye(ThreeEyeBean three_eye) {
                this.three_eye = three_eye;
            }

            public double getLefteye() {
                return lefteye;
            }

            public void setLefteye(double lefteye) {
                this.lefteye = lefteye;
            }

            public FiveEyeBean getFive_eye() {
                return five_eye;
            }

            public void setFive_eye(FiveEyeBean five_eye) {
                this.five_eye = five_eye;
            }

            public static class OneEyeBean {
                /**
                 * righteye_empty_length : 24.21
                 * righteye_empty_ratio : 0.8
                 * righteye_empty_result : righteye_empty_normal
                 */

                private double righteye_empty_length;
                private double righteye_empty_ratio;
                private String righteye_empty_result;

                public double getRighteye_empty_length() {
                    return righteye_empty_length;
                }

                public void setRighteye_empty_length(double righteye_empty_length) {
                    this.righteye_empty_length = righteye_empty_length;
                }

                public double getRighteye_empty_ratio() {
                    return righteye_empty_ratio;
                }

                public void setRighteye_empty_ratio(double righteye_empty_ratio) {
                    this.righteye_empty_ratio = righteye_empty_ratio;
                }

                public String getRighteye_empty_result() {
                    return righteye_empty_result;
                }

                public void setRighteye_empty_result(String righteye_empty_result) {
                    this.righteye_empty_result = righteye_empty_result;
                }
            }

            public static class ThreeEyeBean {
                /**
                 * eyein_length : 24.31
                 * eyein_ratio : 1.41
                 * eyein_result : eyein_long
                 */

                private double eyein_length;
                private double eyein_ratio;
                private String eyein_result;

                public double getEyein_length() {
                    return eyein_length;
                }

                public void setEyein_length(double eyein_length) {
                    this.eyein_length = eyein_length;
                }

                public double getEyein_ratio() {
                    return eyein_ratio;
                }

                public void setEyein_ratio(double eyein_ratio) {
                    this.eyein_ratio = eyein_ratio;
                }

                public String getEyein_result() {
                    return eyein_result;
                }

                public void setEyein_result(String eyein_result) {
                    this.eyein_result = eyein_result;
                }
            }

            public static class FiveEyeBean {
                /**
                 * lefteye_empty_length : 24.23
                 * lefteye_empty_ratio : 0.8
                 * lefteye_empty_result : lefteye_empty_short
                 */

                private double lefteye_empty_length;
                private double lefteye_empty_ratio;
                private String lefteye_empty_result;

                public double getLefteye_empty_length() {
                    return lefteye_empty_length;
                }

                public void setLefteye_empty_length(double lefteye_empty_length) {
                    this.lefteye_empty_length = lefteye_empty_length;
                }

                public double getLefteye_empty_ratio() {
                    return lefteye_empty_ratio;
                }

                public void setLefteye_empty_ratio(double lefteye_empty_ratio) {
                    this.lefteye_empty_ratio = lefteye_empty_ratio;
                }

                public String getLefteye_empty_result() {
                    return lefteye_empty_result;
                }

                public void setLefteye_empty_result(String lefteye_empty_result) {
                    this.lefteye_empty_result = lefteye_empty_result;
                }
            }
        }

        public static class FaceBean {
            /**
             * tempus_length : 112.15
             * zygoma_length : 135.36
             * face_length : 219.41
             * mandible_length : 104.11
             * E : 116.0
             * ABD_ratio : 1.31:1:1.45
             * face_type : pointed_face
             */

            private double tempus_length;
            private double zygoma_length;
            private double face_length;
            private double mandible_length;
            private double E;
            private String ABD_ratio;
            private String face_type;

            public double getTempus_length() {
                return tempus_length;
            }

            public void setTempus_length(double tempus_length) {
                this.tempus_length = tempus_length;
            }

            public double getZygoma_length() {
                return zygoma_length;
            }

            public void setZygoma_length(double zygoma_length) {
                this.zygoma_length = zygoma_length;
            }

            public double getFace_length() {
                return face_length;
            }

            public void setFace_length(double face_length) {
                this.face_length = face_length;
            }

            public double getMandible_length() {
                return mandible_length;
            }

            public void setMandible_length(double mandible_length) {
                this.mandible_length = mandible_length;
            }

            public double getE() {
                return E;
            }

            public void setE(double E) {
                this.E = E;
            }

            public String getABD_ratio() {
                return ABD_ratio;
            }

            public void setABD_ratio(String ABD_ratio) {
                this.ABD_ratio = ABD_ratio;
            }

            public String getFace_type() {
                return face_type;
            }

            public void setFace_type(String face_type) {
                this.face_type = face_type;
            }
        }

        public static class JawBean {
            /**
             * jaw_width : 29.61
             * jaw_length : 15.03
             * jaw_type : sharp_jaw
             */

            private double jaw_width;
            private double jaw_length;
            private String jaw_type;

            public double getJaw_width() {
                return jaw_width;
            }

            public void setJaw_width(double jaw_width) {
                this.jaw_width = jaw_width;
            }

            public double getJaw_length() {
                return jaw_length;
            }

            public void setJaw_length(double jaw_length) {
                this.jaw_length = jaw_length;
            }

            public String getJaw_type() {
                return jaw_type;
            }

            public void setJaw_type(String jaw_type) {
                this.jaw_type = jaw_type;
            }
        }

        public static class EyebrowBean {
            /**
             * brow_width : 33.12
             * brow_height : 5.17
             * brow_uptrend_angle : 21.04
             * brow_camber_angle : 8.76
             * brow_thick : 3.17
             * eyebrow_type : bushy_eyebrows
             */

            private double brow_width;
            private double brow_height;
            private double brow_uptrend_angle;
            private double brow_camber_angle;
            private double brow_thick;
            private String eyebrow_type;

            public double getBrow_width() {
                return brow_width;
            }

            public void setBrow_width(double brow_width) {
                this.brow_width = brow_width;
            }

            public double getBrow_height() {
                return brow_height;
            }

            public void setBrow_height(double brow_height) {
                this.brow_height = brow_height;
            }

            public double getBrow_uptrend_angle() {
                return brow_uptrend_angle;
            }

            public void setBrow_uptrend_angle(double brow_uptrend_angle) {
                this.brow_uptrend_angle = brow_uptrend_angle;
            }

            public double getBrow_camber_angle() {
                return brow_camber_angle;
            }

            public void setBrow_camber_angle(double brow_camber_angle) {
                this.brow_camber_angle = brow_camber_angle;
            }

            public double getBrow_thick() {
                return brow_thick;
            }

            public void setBrow_thick(double brow_thick) {
                this.brow_thick = brow_thick;
            }

            public String getEyebrow_type() {
                return eyebrow_type;
            }

            public void setEyebrow_type(String eyebrow_type) {
                this.eyebrow_type = eyebrow_type;
            }
        }

        public static class EyesBean {
            /**
             * eye_width : 20.35
             * eye_height : 8.66
             * angulus_oculi_medialis : 58.32
             * eyes_type : big_eyes
             */

            private double eye_width;
            private double eye_height;
            private double angulus_oculi_medialis;
            private String eyes_type;

            public double getEye_width() {
                return eye_width;
            }

            public void setEye_width(double eye_width) {
                this.eye_width = eye_width;
            }

            public double getEye_height() {
                return eye_height;
            }

            public void setEye_height(double eye_height) {
                this.eye_height = eye_height;
            }

            public double getAngulus_oculi_medialis() {
                return angulus_oculi_medialis;
            }

            public void setAngulus_oculi_medialis(double angulus_oculi_medialis) {
                this.angulus_oculi_medialis = angulus_oculi_medialis;
            }

            public String getEyes_type() {
                return eyes_type;
            }

            public void setEyes_type(String eyes_type) {
                this.eyes_type = eyes_type;
            }
        }

        public static class NoseBean {
            /**
             * nose_width : 28.12
             * nose_type : thick_nose
             */

            private double nose_width;
            private String nose_type;

            public double getNose_width() {
                return nose_width;
            }

            public void setNose_width(double nose_width) {
                this.nose_width = nose_width;
            }

            public String getNose_type() {
                return nose_type;
            }

            public void setNose_type(String nose_type) {
                this.nose_type = nose_type;
            }
        }

        public static class MouthBean {
            /**
             * mouth_height : 11.37
             * mouth_width : 42.34
             * lip_thickness : 6.8
             * angulus_oris : 85.67
             * mouth_type : thin_lip
             */

            private double mouth_height;
            private double mouth_width;
            private double lip_thickness;
            private double angulus_oris;
            private String mouth_type;

            public double getMouth_height() {
                return mouth_height;
            }

            public void setMouth_height(double mouth_height) {
                this.mouth_height = mouth_height;
            }

            public double getMouth_width() {
                return mouth_width;
            }

            public void setMouth_width(double mouth_width) {
                this.mouth_width = mouth_width;
            }

            public double getLip_thickness() {
                return lip_thickness;
            }

            public void setLip_thickness(double lip_thickness) {
                this.lip_thickness = lip_thickness;
            }

            public double getAngulus_oris() {
                return angulus_oris;
            }

            public void setAngulus_oris(double angulus_oris) {
                this.angulus_oris = angulus_oris;
            }

            public String getMouth_type() {
                return mouth_type;
            }

            public void setMouth_type(String mouth_type) {
                this.mouth_type = mouth_type;
            }
        }
    }
}
