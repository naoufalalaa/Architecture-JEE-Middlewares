# Digital Banking Front End

> Faisant suite à l'api Spring de Digital banking, cette application Angular est dans la perspective de continuité.



## Environnement de travail et outils

- [x] Backend : Spring

<img src="https://user-images.githubusercontent.com/61352259/170826693-00c60eae-ee6d-4d97-a7dd-4bde5699a4c3.png" width="100px"/>

- [x] Frontend : Angular | tailwind

<img src="https://user-images.githubusercontent.com/61352259/170826855-81048c33-d919-4a86-a3db-58b442f34248.png" width="100px"/> || <img src="https://user-images.githubusercontent.com/61352259/170827391-5dd0d860-5def-49c3-9db9-46ae725fac5c.png" width="50px"/>

- [x] SGBD : Mysql

#### Installation de Angular

``` cli
npm install -g @angular/cli
```

Initialisation du projet : 
```
ng new DigitalBanking-front
```

#### Installation de tailwind
```
npm install -D tailwindcss postcss autoprefixer
npx tailwindcss init
```


### Models
 
> On crée les models dont on aura besoins pour le stockage ou l'envoie des données: 

#### Customer : 

``` ts
export interface Customer{
  id:     number;
  name:   string;
  email:  string;
}
```

#### Account : 

``` ts
export interface AccountHistory {
  accountId:            string;
  balance:              number;
  currentPage:          number;
  totalPages:           number;
  pageSize:             number;
  accountOperationDTOS: AccountOperationDTO[];
}

export interface AccountOperationDTO {
  id:            number;
  operationDate: Date;
  amount:        number;
  type:          string;
  description:   string;
}

export interface CustomerDTO {
  id:     number;
  name:   string;
  email:  string;
}

export interface AccountDTO {
  type:           string;
  id:             string;
  balance:        number;
  createdAt:      Date;
  accountStatus?: any;

}
```

### Services : 

Pour communiquer avec le backend il est evident que l'on aura besoins d'un module ***http*** pour le faire : 

``` ts
import {HttpClient} from "@angular/common/http";
```
 
#### Customer : 

``` ts
export class CustomerService {
  constructor(private http:HttpClient) { }

  public getCustomers():Observable<Array<Customer>>{
    return this.http.get<Array<Customer>>(environment.backendHost+"/customers");
  }
  public searchCustomers(keyword:string):Observable<Array<Customer>>{
    return this.http.get<Array<Customer>>(environment.backendHost+"/customers/search?keyword="+keyword);
  }
  public saveCustomers(customer:Customer):Observable<Customer>{
    return this.http.post<Customer>(environment.backendHost+"/customers",customer);
  }
  public deleteCustomer(id:number){
    return this.http.delete(environment.backendHost+"/customers/"+id);
  }
}
```

#### Account : 
``` ts
export class AccountService {

  constructor(private http:HttpClient) { }

  public getAccount(accountId:string,page:number,size:number):Observable<AccountHistory>{
    return this.http.get<AccountHistory>(environment.backendHost+"/accounts/"+accountId+"/pageOperations?" +
      "page="+page+"&size"+size);
  }

  public getAccountByCustomerID(customerID: number):Observable<Array<AccountDTO>>{
    return this.http.get<Array<AccountDTO>>(environment.backendHost+"/account/"+customerID);
  }
  public debit(accountId:string,amount:number,description:string){
    let data={accountId,amount ,description}
    return this.http.post(environment.backendHost+"/accounts/debit",data);
  }
  public credit(accountId:string,amount:number,description:string){
    let data={accountId,amount,description}
    return this.http.post(environment.backendHost+"/accounts/credit",data);
  }
  public transfer(accountSourceId:string,accountDestinationId:string,amount:number){
    let data={accountSourceId,accountDestinationId,amount}
    return this.http.post(environment.backendHost+"/accounts/transfer",data);
  }
}
```

## Réalisation
<img src = "https://user-images.githubusercontent.com/61352259/170828120-1779e6ed-abf0-4c69-86cf-236c45993735.png" width="800px" />

<img src = "https://user-images.githubusercontent.com/61352259/170827771-98d20995-0556-4108-9ca6-a2420815bc29.png" width="500px" />
<img src = "https://user-images.githubusercontent.com/61352259/170827877-538efa3b-acdd-4356-9aba-7ae9ecdd92bd.png" width="500px" />
<img src = "https://user-images.githubusercontent.com/61352259/170827918-16603efd-8a5e-49de-bef6-680e30e4e586.png" width="500px" />
<img src = "https://user-images.githubusercontent.com/61352259/170827964-131c2d78-23f3-4360-9a79-e7f6373e0096.png" width="500px" />
<img src = "https://user-images.githubusercontent.com/61352259/170827983-9e4e0457-9ead-4a33-a492-4df4bcf33e56.png" width="500px" />
<img src = "https://user-images.githubusercontent.com/61352259/170827996-a18ffbcb-caea-4acc-8e96-08c7e966cbbd.png" width="500px" />
<img src = "https://user-images.githubusercontent.com/61352259/170828012-0b7ee92d-de23-4c68-90b8-e517fe22cb4b.png" width="500px" />
<img src = "https://user-images.githubusercontent.com/61352259/170828047-4f2f7f27-6347-49b5-8bbf-58ecd123b8a9.png" width="500px" />
<img src = "https://user-images.githubusercontent.com/61352259/170828073-4ad4ce85-2235-42d6-b02c-5cbc7db1bcac.png" width="500px" />
<img src = "https://user-images.githubusercontent.com/61352259/170828069-1fa6c78d-c4c9-4cf1-b20c-5e70d42c753a.png" width="500px" />




