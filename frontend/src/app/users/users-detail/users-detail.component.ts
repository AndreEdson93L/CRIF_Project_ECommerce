import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/interfaces/user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-users-detail',
  templateUrl: './users-detail.component.html',
  styleUrls: ['./users-detail.component.css']
})
export class UserDetailComponent {
  @Input() user?: User;

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.getUser();
  }
  
  getUser(): void {
    console.log("asas3")
    /* snapshot only gets the initial value of the parameter map with this technique. Use the observable paramMap approach if there's a possibility that the router could re-use the component. */
    /* in this app There's no way to navigate from one hero detail to another hero detail without visiting the list component in between.  */
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.userService.getUser(id)
      .subscribe(user => this.user = user);
  }
  gotoUsers() {
    this.router.navigate(['/users']);
  }

}
