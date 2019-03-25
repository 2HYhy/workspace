import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MessageService {  //该类的作用近视用于发送消息，与数据的获取并无任何关系

  constructor() { }

  messages: string[] = [];

  add(message: string) {
    this.messages.push(message);
  }

  clear() {
    this.messages = [];
  }
}
