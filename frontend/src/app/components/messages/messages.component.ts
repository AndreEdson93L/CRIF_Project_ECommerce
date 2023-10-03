import { Component } from '@angular/core';
import { Messageservice } from 'src/app/services/messageservice.service';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: []
})
export class MessagesComponent {
  constructor(public messageService: Messageservice) {}
}
