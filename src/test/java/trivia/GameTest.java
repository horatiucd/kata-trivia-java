
package trivia;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class GameTest {

	@Test
	public void characterizationTest() {
		// runs 10.000 "random" games to see the output of the old and new code matches
		for (int seed = 1; seed < 10_000; seed++) {
			testSeed(seed, false);
		}
	}

	private void testSeed(int seed, boolean printExpected) {
		String expectedOutput = extractOutput(new Random(seed), new Game());
		if (printExpected) {
			System.out.println(expectedOutput);
		}
		String actualOutput = extractOutput(new Random(seed), new TriviaGame());
		assertEquals("Change detected for seed " + seed +
						 ". To breakpoint through it, run this seed alone using the (ignored) test below",
			expectedOutput, actualOutput);
	}

	@Test
	//@Ignore("enable back and set a particular seed to see the output")
	public void oneSeed() {
		testSeed(1, true);
	}

	private String extractOutput(Random rand, IGame aGame) {
		PrintStream old = System.out;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (PrintStream inMemory = new PrintStream(baos)) {
			// WARNING: System.out.println() doesn't work in this try {} as the system out is captured and recorded in memory
			System.setOut(inMemory);

			aGame.enrollPlayer("Chet");
			aGame.enrollPlayer("Pat");
			aGame.enrollPlayer("Sue");

			boolean notAWinner;
			do {
				aGame.playTurn(rand.nextInt(5) + 1);

				if (rand.nextInt(9) == 7) {
					notAWinner = aGame.onWrongAnswer();
				} else {
					notAWinner = aGame.onCorrectAnswer();
				}

			} while (notAWinner);
		} finally {
			System.setOut(old);
		}

		return baos.toString();
	}
}
