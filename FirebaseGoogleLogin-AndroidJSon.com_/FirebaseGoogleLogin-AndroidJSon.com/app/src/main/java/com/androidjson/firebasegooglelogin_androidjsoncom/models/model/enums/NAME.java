package com.androidjson.firebasegooglelogin_androidjsoncom.models.model.enums;

/**
 * Created by F.Arian on 01.12.17.
 */
public enum NAME {
    OTHER {
        @Override
        public TYPE getType(TYPE type) {
            for (int i = 0; i < TYPE.values().length; i++) {
                if (TYPE.values()[i].equals(type)) {
                    return TYPE.values().clone()[i];
                }
            }
            return TYPE.RT_CAMERA;
        }
    },
    SENTINEL {
        @Override
        public TYPE getType(TYPE type) {
            for (int i = 0; i < TYPE.values().length; i++) {
                if (TYPE.values()[i].equals(type)) {
                    return TYPE.values().clone()[i];
                }
            }
            return TYPE.RT_CAMERA;
        }
    },
    GAMMAMAT {
        @Override
        public TYPE getType(TYPE type) {
            for (int i = 0; i < TYPE.values().length; i++) {
                if (TYPE.values()[i].equals(type)) {
                    return TYPE.values().clone()[i];
                }
            }
            return TYPE.RT_CAMERA;
        }
    },
    Dandong_XY_Electric {
        @Override
        public TYPE getType(TYPE type) {
            for (int i = 0; i < TYPE.values().length; i++) {
                if (TYPE.values()[i].equals(type)) {
                    return TYPE.values().clone()[i];
                }
            }
            return TYPE.RT_CAMERA;
        }
    },
    OSERIX {
        @Override
        public TYPE getType(TYPE type) {
            for (int i = 0; i < TYPE.values().length; i++) {
                if (TYPE.values()[i].equals(type)) {
                    return TYPE.values().clone()[i];
                }
            }
            return TYPE.RT_CAMERA;
        }
    }, AGFA {
        @Override
        public TYPE getType(TYPE type) {
            for (int i = 0; i < TYPE.values().length; i++) {
                if (TYPE.values()[i].equals(type)) {
                    return TYPE.values().clone()[i];
                }
            }
            return null;

        }
    }, KODAK {
        @Override
        public TYPE getType(TYPE type) {
            for (int i = 0; i < TYPE.values().length; i++) {
                if (TYPE.values()[i].equals(type)) {
                    return TYPE.values().clone()[i];
                }
            }
            return null;
        }
    }, FUJIFILM {
        @Override
        public TYPE getType(TYPE type) {
            for (int i = 0; i < TYPE.values().length; i++) {
                if (TYPE.values()[i].equals(type)) {
                    return TYPE.values().clone()[i];
                }
            }
            return null;
        }
    },
    NDT {
        @Override
        public TYPE getType(TYPE type) {
            for (int i = 0; i < TYPE.values().length; i++) {
                if (TYPE.values()[i].equals(type)) {
                    return TYPE.values().clone()[i];
                }
            }
            return null;
        }
    };

    public abstract TYPE getType(TYPE type);


}
