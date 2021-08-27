import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Books } from '../books';
import { BookService } from '../book.service';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {

  books!: Books[];
  bookz: (any);
  searchInput!: string;
  titleInput!: string;
  authorInput!: string;
  searchBool: boolean = false;

  constructor(private bookService: BookService, 
    private router: Router) { }

  ngOnInit(): void 
  {
    this.getBooks();
  }

  private getBooks()
  {
    this.bookService.getBooksList().subscribe(data => {
      this.books = data;
    })
  }

  bookDetails(id: number){
    this.router.navigate(['book-details', id]);
  }

  updateBook(id: number)
  {
    this.router.navigate(['update-book', id]);
  }

  deleteBook(id: number)
  {
    this.bookService.deleteBook(id).subscribe( data => {
      console.log(data);
      this.getBooks();
    })
  }

  search()
  {
    for (let bookz of this.books)
    {
      console.log(bookz.title);
      if(bookz.title.toLowerCase() == this.searchInput.toLowerCase() || bookz.authors.toLowerCase() == this.searchInput.toLowerCase())
      {
        this.searchBool = true;
        this.bookDetails(bookz.id);
      }
    }

    //This is to allow user to enter details page to see error message saying that book does not exist
    if (this.searchBool == false)
    {
    this.bookDetails(parseInt(this.searchInput)+1000000000);
    }
  }

  getSpecificBookByTitle(title:string)
  {
    this.bookService.getBookByTitle(title).subscribe(data => {
      this.bookz = data;
      this.books = this.bookz;
      this.router.navigate(['books']);
    })

  }

  searchTitle()
  {
    this.getSpecificBookByTitle(this.titleInput);
  }




  getSpecificBookByAuthor(author:string)
  {
    this.bookService.getBookByAuthor(author).subscribe(data => {
      this.bookz = data;
      this.books = this.bookz;
      this.router.navigate(['books']);
    })

  }

  searchAuthor()
  {
    this.getSpecificBookByAuthor(this.authorInput);
  }

}
