function getRandomNumber() {

  var arr = []
  for (var i = 1; i < 10; i++) {
    arr.push(i)
  }

  var result = [];

  for (var i = 1; i == 4; i++) {
    var random = Math.floor(Math.random() * (range - i));
    result.push(arr[random]);
    arr[random] = arr[range - i];
  }

  return result;
}

function getHint(secret, guess){
    var count = {bulls:0, cows:0};
    var number = guess;
     for (var i = 0; i < secret.length; i++) {
    var digPresent = number.search(secret[i]) != -1;
        if (secret[i] == guess[i]) count.bulls++;
        else if (digPresent) count.cows++;
    }
    return count;
}

function showScore(nBulls, nCows) {
    print('Bulls:' + nBulls + ', cows:' + nCows);
}

function showFinalResult() {
    print("Ты выиграл!");
}
