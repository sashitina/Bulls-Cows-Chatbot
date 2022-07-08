require: slotfilling/slotFilling.sc
  module = sys.zb-common
  
require: common.js
    module = sys.zb-common
    
require: getNumber.js
    
theme: /

    state: Правила
        q!: $regex</start>
        intent!: /Давай поиграем
        script:
        a: Сыграем в "Быки и коровы". Я загадаю 4-значное число с неповторяющимися цифрами, ты попытаешься отгадать. Каждая угаданная цифра - это "корова". Цифра, угаданная вплоть до позиции - "бык". \n\n Например, я загадал число «3219». \n\n Твое число: «2310». \n\n Результат: один «бык» и две «коровы». \n\n Начнем?
        go!: /Правила/Согласен?
        
        state: Согласен?
            
            state: Да
                intent: /Согласие
                go!: /Игра

            state: Нет
                intent: /Несогласие
                a: Жаль! Если передумаешь - скажи "давай поиграем".
                
    state: Игра
        # сгенерируем случайное число и перейдем в стейт /Проверка
        script:
            $session.number = getRandomNumber();
            $reactions.answer("Загадано {{$session.number}}");
            $reactions.transition("/Проверка");
            
    state: Проверка
        intent: /Число
        
        if: $parseTree._Number > -1 & $parseTree._Number < 1
            
            script:
                $reactions.answer("Ты ввел нули. Введи 4-значное число.");
                
        else:
            
            script:
                # сохраняем введенное пользователем число
                $session.user_number = $parseTree._Number;
              
                $session.win = ("4 быка, 0 коров");
                $session.check = getHint($session.number, $session.user_number)

                # проверяем, угадал ли пользователь загаданное число и выводим соответствующую реакцию
                if ($session.check == $session.win) {
                    $reactions.answer("Ты выиграл! Сыграем еще?");
                    $reactions.transition("/Правила/Согласен?");
                }
                else $reactions.answer("{{ $session.check }}");
        

    state: NoMatch || noContext = true
        event!: noMatch
        random:
            a: Я не понял.
            a: Что ты имеешь ввиду?
            a: Ничего не пойму