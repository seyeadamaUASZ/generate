import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material';

declare var $:any;

@Component({
  selector: 'app-chatbot',
  templateUrl: './chatbot.component.html',
  styleUrls: ['./chatbot.component.scss']
})
export class ChatbotComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<ChatbotComponent>) { }

  closeDialog() {
    this.dialogRef.close();
    //this.router.navigate(['generateurotp']);
  }

  ngOnInit(): void {
    const msgerForm = get(".msger-inputarea");
    const msgerInput = get(".msger-input");
    const msgerChat = get(".msger-chat");

    //const BOT_IMG = "https://image.flaticon.com/icons/svg/327/327779.svg";
    //const PERSON_IMG = "https://image.flaticon.com/icons/svg/145/145867.svg";
    const BOT_IMG="assets/images/bot.png"
    const PERSON_IMG="assets/images/iconeperson.jpg"
    const BOT_NAME = "Indus Bot";
    const PERSON_NAME = "Vous";

    msgerForm.addEventListener("submit", event => {
      event.preventDefault();

      const msgText = msgerInput.value;
      if (!msgText) return;

      appendMessage(PERSON_NAME,PERSON_IMG, "right", msgText);
      msgerInput.value = "";
      botResponse(msgText);
    });

    function appendMessage(name, img, side, text) {
      //   Simple solution for small apps
      console.log(img)
      const msgHTML = `
     <div class="msg ${side}-msg">
     <div class="msg-img" style="background-image: url(${img})"></div>

    <div class="msg-bubble">
     <div class="msg-info">
      <div class="msg-info-name">${name}</div>
      <div class="msg-info-time">${formatDate(new Date())}</div>
    </div>

    <div class="msg-text">${text}</div>
  </div>
</div>
`;

      msgerChat.insertAdjacentHTML("beforeend", msgHTML);
      msgerChat.scrollTop += 500;
      // msgerChat.scrollTop(msgerChat.prop("scrollHeight"))
    }

    function botResponse(rawText) {

      var data = JSON.stringify({
        "message": rawText
      });

      var xhr = new XMLHttpRequest();
      xhr.withCredentials = false;

      xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
          console.log(this.responseText);
          const msgText = this.responseText;
          appendMessage(BOT_NAME, BOT_IMG, "left", msgText);
        }
      });
      //https://adamaseyedev.pythonanywhere.com/ https://chatbot.gainde2000.sn:8084/chat
      xhr.open("POST", "http://adamaseyedev.pythonanywhere.com/chat");
      xhr.setRequestHeader("content-type", "application/json");
      xhr.send(data);
      console.log(data)
    }
    //
    function get(selector, root = document) {
      return root.querySelector(selector);
    }

    function formatDate(date) {
      const dd = String(date.getDate()).padStart(2, '0');
      const mm = String(date.getMonth() + 1).padStart(2, '0');
      const yyyy = date.getFullYear();
      const h = "0" + date.getHours();
      const m = "0" + date.getMinutes();

      return `${dd}-${mm}-${yyyy} ${h.slice(-2)}:${m.slice(-2)}`;
    }

    $(".chat-bot-icon").click(function (e) {
      $(this).children('img').toggleClass('hide');
      $(this).children('svg').toggleClass('animate');
      $('.chat-screen').toggleClass('show-chat');
  });
  // $('.chat-mail button').click(function () {
  //     $('.chat-mail').addClass('hide');
  //     $('.chat-body').removeClass('hide');
  //     $('.chat-input').removeClass('hide');
  //     $('.chat-header-option').removeClass('hide');
  // });
  // $('.end-chat').click(function () {
  //     $('.chat-body').addClass('hide');
  //     $('.chat-input').addClass('hide');
  //     $('.chat-session-end').removeClass('hide');
  //     $('.chat-header-option').addClass('hide');
  // });
  }

}
