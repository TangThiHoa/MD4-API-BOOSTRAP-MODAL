let question = $('#question')
let answers = $('#answers')
let temp;
let answersInp = $('#answersInp')
let questionInp = $('#questionInp')
let modal = $('#exampleModal')
let count;
let validQues = $('#validQues')

function getQuestion() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080",
        success: function (questionIn) {
            question.text("Câu hỏi: " + questionIn[0].content);
            temp = questionIn[1];
            getAnswer(questionIn);
        }
    })
}

function getAnswer(questionIn) {
    let str = "";
    if(questionIn[0].type === true) {
        for (let i = 0; i < questionIn[1].length; i++) {
            str += `<input type="checkbox" value="${questionIn[1][i].id}" name="answer"> <h4 style="display: inline">${questionIn[1][i].name}</h4> <br>`
        }
        answers.html(str)
    }
    if(questionIn[0].type === false) {
        for (let i = 0; i < questionIn[1].length; i++) {
            str += `<input type="radio" value="${questionIn[1][i].id}" name="answer"> <h4 style="display: inline">${questionIn[1][i].name}</h4> <br>`
        }
        answers.html(str)
    }
}

function sendAnswers() {
    let answer = $("input:checked")
    let arr = [];
    for(let i = 0; i < answer.length; i++) {
        arr.push(answer[i].value)
    }
    check(arr, temp)
}

function check(arr, temp) {
    let correct = [];
    for (let i = 0; i < temp.length; i++) {
        if (temp[i].true === true) {
            for (let j = 0; j < arr.length; j++) {
                if (temp[i].id == arr[j]) {
                    correct.push(temp[i]);
                }
            }
        }
    }
    alert("bạn làm đúng" + correct.length)
    location.reload()
}

function addAnswerForm() {
    count += 1;
    let str = `Câu trả lời ${count}: <br>
                    <input class="cBAnswer" type="checkbox"> <input class="inpAnswer" type="text" style="width: 96%">`
    answersInp.html(answersInp.html() + str)
}

function showCreateQuestionForm() {
    resetValue()
    count = 2
    let str = `Câu trả lời 1: <br>
                    <input class="cBAnswer" type="checkbox"> <input class="inpAnswer" type="text" style="width: 96%">
                    Câu trả lời 2: <br>
                    <input class="cBAnswer" type="checkbox"> <input class="inpAnswer" type="text" style="width: 96%">`
    answersInp.html(str)
    modal.modal('show')
}

function save() {
    let inpAnswer = $(".inpAnswer")
    let cBAnswer = $(".cBAnswer")
    let inpQuestion = document.getElementById("questionInp")
    let temp = []
    let status;
    if (count == 2) {
        status = false
    } else {
        status = true;
    }
    let question = {
        content: inpQuestion.value,
        type: status
    }
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: 'Post',
        url: "http://localhost:8080",
        data: JSON.stringify(question),
        success: function (question1) {
            let questionId = question1.id;
            for (let i = 0; i < cBAnswer.length; i++) {
                if(cBAnswer[i].checked == true) {
                    temp.push(true)
                }
                else {
                    temp.push(false)
                }
            }
            for (let i = 0; i < inpAnswer.length; i++) {
                let answer = {
                    name: inpAnswer[i].value,
                    question: {
                        id: questionId
                    },
                    true: temp[i]
                }
                saveAnswer(answer)
            }
            modal.modal('hide')
        },
        error: function (error) {
            console.log(error)
            validQues.text(error.responseJSON.content)
        }
    })
}

function saveAnswer(answer) {
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: 'Post',
        url: "http://localhost:8080/answers",
        data: JSON.stringify(answer),
        success: function () {
            console.log('ok')
        }
    })
}

function resetValue() {
    questionInp.text("")
    validQues.text("")
}