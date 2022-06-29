function getRandomNumber(){
  return shuffle( "0123456789".split('') ).join('').substring(0,4);
}

function shuffle(o){
    for(var j, x, i = o.length; i; j = Math.floor(Math.random() * i), x = o[--i], o[i] = o[j], o[j] = x);
    return o;
}

function getHint(secret, guess) {
  var map = {};
  var A = 0;
  var B = 0;
  for (var i = 0; i < 10; i++) map[i] = 0;
  for (i = 0; i < secret.length; i++) {
    if (secret[i] === guess[i]) A++;
    else {
      map[secret[i]]++;
      B += map[secret[i]] <= 0 ? 1 : 0;
      map[guess[i]]--;
      B += map[guess[i]] >= 0 ? 1 : 0;
    }
  }
  return 'Быков:' + A + ', коров:' + B;
}

function showScore(nBulls, nCows) {
    print('Bulls:' + nBulls + ', cows:' + nCows);
}

function showFinalResult() {
    print("Ты выиграл!");
}
