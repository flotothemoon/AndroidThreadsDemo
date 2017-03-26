package at.wels.htl.a5ahit.androidthreadsdemo.threading;

import android.os.SystemClock;
import android.util.Log;

public final class PrimeUtils {

    public static boolean isPrime(long prime) {
        long sqrRoot = (long) Math.sqrt(prime);

        for (long i = 2; i <= sqrRoot; i++) {
            if (prime % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void FindNextPrime(long minPrime) {
        FindNextPrime(minPrime, null);
    }

    public static void FindNextPrime(long minPrime, PrimeTester tester) {
        for (long prime = minPrime; ; prime++) {
            Log.w("PrimeThread", "Checking: " + prime);

            if (tester != null) {
                tester.test(prime);
            }

            if (isPrime(prime)) {
                Log.w("PrimeThread", prime + " is a prime that is at least " + minPrime);
                break;
            }

            SystemClock.sleep(1000);
        }
    }
}
