public class TestNested {
    public int foo(int val) {
        if (val < 0) {
            return -1;
        } else if (val == 0) {
            if (true) {
                return -2;
            }
        }

        int i = 0;
        while (i < 3) {
            val++;
        }

        int j = 1;
        switch(j) {
            case 0:
                val++;
                break;
            case 1:
                val--;
                break;
        }

        for (int k = 0; k < 1; k++) {
            do {
                int w = 0;
            } while (false)
        }

        return val;
    }
}