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
            $session = {}
        a: Сыграем в "Быки и коровы".
        a: Начнем?
        go!: /Правила/Согласен?
        
        state: Согласен?
            buttons:
                "Играем!" -> ./Да
                "Не сегодня" -> ./Нет
            
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
            # $reactions.answer("Загадано {{$session.number}}");
            $reactions.transition("/Проверка");
            
    state: Проверка
        intent: /Число
        script:
            # сохраняем введенное пользователем число
            $session.user_number = $parseTree._Number;
            $session.win = ("Быков: 4, коров: 0");
            $session.check = getHint($session.number, $session.user_number)

            # проверяем угадал ли пользователь загаданное число и выводим соответствующую реакцию
            if ($session.check == $session.win) {
                $reactions.answer("Ты выиграл! Сыграем еще?");
                $reactions.transition("/Правила/Согласен?");
            }
            else
                $reactions.answer("{{ $session.check }}");
                

    state: NoMatch || noContext = true
        event!: noMatch
        random:
            a: Я не понял.
            a: Что ты имеешь ввиду?
            a: Ничего не пойму