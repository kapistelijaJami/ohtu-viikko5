package ohtu;

public class TennisGame {
    
    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name)) {
            player1Score++;
		} else {
            player2Score++;
		}
    }

    public String getScore() {
        if (player1Score==player2Score) {
            return returnScoreWhenPlayersHaveSamePoints();
        } else if (player1Score>=4 || player2Score>=4) {
            return returnScoreWhenPlayerHas4OrMorePoints();
        } else {
            return returnScoreWhenPlayersHave1To3Points();
        }
    }
	
	public String returnScoreWhenPlayersHaveSamePoints() {
		switch (player1Score) {
			case 0:
				return "Love-All";
			case 1:
				return "Fifteen-All";
			case 2:
				return "Thirty-All";
			case 3:
				return "Forty-All";
			default:
				return "Deuce";
		}
	}
	
	public String returnScoreWhenPlayerHas4OrMorePoints() {
		int minusResult = player1Score - player2Score;
		if (minusResult == 1) {
			return "Advantage player1";
		} else if (minusResult == -1) {
			return "Advantage player2";
		} else if (minusResult >= 2) {
			return "Win for player1";
		} else {
			return "Win for player2";
		}
	}
	
	public String returnScoreWhenPlayersHave1To3Points() {
		String score = "";
		int tempScore = 0;
		
		for (int i=1; i<3; i++) {
			if (i==1) {
				tempScore = player1Score;
			} else {
				score += "-";
				tempScore = player2Score;
			}
			
			score += switchFor1To3Points(tempScore);
		}
		return score;
	}
	
	public String switchFor1To3Points(int tempScore) {
		switch(tempScore) {
			case 0:
				return "Love";
			case 1:
				return "Fifteen";
			case 2:
				return "Thirty";
			case 3:
				return "Forty";
			default:
				return "";
		}
	}
}