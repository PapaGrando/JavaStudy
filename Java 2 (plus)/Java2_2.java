class Scratch {
    public static void main(String[] args) {

        Converter conv = new Converter();

        String[][] correctArray = {
                {
                        "3", "5", "2", "5"
                },

                {
                        "3", "5", "2", "5"
                },

                {
                        "3", "5", "2", "5"
                },

                {
                        "3", "5", "2", "5"
                }
        };

        String[][] incorrectArray = {
                {
                        "3", "5", "2", "5"
                },

                {
                        "3", "5", "2", "5"
                },

                {
                        "3", "5", "2", "5"
                },

                {
                        "3", "5", "2", "g"
                }
        };

        String[][] incorrectSizeArray = {
                {
                        "3", "5", "2", "5"
                },

                {
                        "3", "5", "2"
                },

                {
                        "3", "5", "2"
                },

                {
                        "3", "5", "2"
                }

        };
        try {
            conv.getIntsFromArray(correctArray);
        }catch (ConverterArraySize converterArraySize) {
            System.out.println("Incorrect array size");
            converterArraySize.printStackTrace();
        }catch (ConverterArrayDataException converterArrayDataException){
            System.out.println("Incorrect data in array");
            converterArrayDataException.printStackTrace();
        }

    }

    static class Converter {
        int a;
        public Converter() {
        }

        public void getIntsFromArray(String[][] array) throws ConverterArraySize, ConverterArrayDataException {

            if (array.length != 4)
                throw new ConverterArraySize();

            int sum = 0;

            for (int i = 0; array.length > i; i++) {
                for (int j = 0; array[i].length > j; j++) {

                    if (array[i].length != 4)
                        throw new ConverterArraySize();

                    try {
                        sum += Integer.parseInt(array[i][j]);
                    }catch (NumberFormatException numberFormatException){
                        throw new ConverterArrayDataException();
                    }
                }
            }
            System.out.println(sum);
        }
    }

    static class ConverterArraySize extends Exception {
    }

    static class ConverterArrayDataException extends Exception {
    }
}