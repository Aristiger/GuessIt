package com.aristiger.developer.guessit;

class MovieAux {
    private  String question;
    private String hint;
    private String answer;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "MovieAux{" +
                "question='" + question + '\'' +
                ", hint='" + hint + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
