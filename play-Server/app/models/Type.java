package models;

/**
 * Created by F.Arian on 06.11.17.
 */
public enum Type {

    SAFETY {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }
    }, ANCILLARY_EQUIPMENT {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }
    }, RT_CAMERA {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }
    }, D_ROOM {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }
    }, RT {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }
    }, PT {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }
    }, UT {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }
    }, MT {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }
    }, VT {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }
    }, LT {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }
    }, ET {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }
    },
    STRUCTURIX_D1 {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }
    },
    STRUCTURIX_D2 {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }
    },
    STRUCTURIX_D3 {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }
    },
    STRUCTURIX_D3_sc {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }
    },
    STRUCTURIX_D4 {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }
    },
    STRUCTURIX_D4_W {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }
    },
    STRUCTURIX_D5 {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }
    },
    STRUCTURIX_D6 {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }

    }, STRUCTURIX_D6_W {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }
    },
    STRUCTURIX_D7 {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }
    },
    STRUCTURIX_D8 {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }
    },
    AA400 {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }
    },
    MX125 {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }
    },
    M100 {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }
    },
    DR50 {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }
    },
    T200 {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }
    },
    IX59 {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }
    },
    IX29 {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }
    },
    IX150 {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }
    },
    IX100 {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }
    },
    IX80 {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }
    },
    IX50 {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }
    },
    IX25 {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }
    },
    IX20 {
        @Override
        public MODEL getModel(MODEL model) {
            for (int i = 0; i < MODEL.values().length; i++) {
                if (MODEL.values()[i].equals(model)) {
                    return MODEL.values().clone()[i];
                }
            }
            return null;
        }
    };


    /**
     * @param model
     * @return object from enum MODEL
     */
    public abstract MODEL getModel(MODEL model);

}
