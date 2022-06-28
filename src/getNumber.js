function getRandomNumber(){
  const digits = Array.from({ length: 10 }, (_, i) => i);
  const result = Array.from({ length: 4 }, () => {
    const randIndex = Math.floor(Math.random() * digits.length);
    const digit = digits[randIndex];
    digits.splice(randIndex, 1);
    return digit;
  });
  return Number(result.join(''));
}

function getHint(secret, guess){
    count = {bulls:0, cows:0};
    var number = guess.join('');
     for (var i = 0; i < secret.length; i++) {
    var digPresent = g.search(secret[i]) != -1;
        if (secret[i] == guess[i]) count.bulls++;
        else if (digPresent) count.cows++;
    }
    return count;
}

function showScore(nBulls, nCows) {
    print('Bulls:' + nBulls + ', cows:' + nCows);
}

function showFinalResult(guesses) {
    print('Ты выиграл!);
}
