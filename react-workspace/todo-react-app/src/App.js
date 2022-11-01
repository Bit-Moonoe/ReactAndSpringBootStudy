import './App.css';
import { useEffect, useState } from 'react';
import Todo from './Todo';
import { AppBar, Button, Container, Grid, List, Paper, Toolbar, Typography } from '@mui/material'
import AddTodo from './AddTodo';
import { call, signout } from './service/ApiService';


function App() {

  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    call("/todo", "GET", null)
      .then((response) => {
      setItems(response.data)
      setLoading(false);
      });
  }, []);

  //   const requestOptions = {
  //     method: "GET",
  //     headers:{"Content-Type" : "application/json"}
  //   }

  //   fetch("http://localhost:8015/todo", requestOptions)
  //     .then((response) => response.json())
  //     .then(
  //       (response) => {
  //         setItems(response.data);
  //       },
  //       (error) => {

  //       }
  //     )
  // },[]);

  const deleteItem = (item) => {
    //삭제할 아이템을 찾는다.
    // const newItems = items.filter(e => e.id !== item.id);
    // // console.log(e=>e.id);
    // //삭제할 아이템을 제외한 아이템을 다시 배열에 저장한다.
    // setItems([...newItems])
    call("/todo", "DELETE", item)
      .then((response) => setItems(response.data));
  }

  const editItem = (item) => {
    call("/todo", "PUT", item)
      .then((response) => setItems(response.data))
  }


  const addItem = (item) => {
    // item.id = "ID-" + items.length;
    // item.done = false;
    // //업데이트는 반드시 setItems로 하고 새 배열을 만들어야 한다.
    // setItems([...items, item]);
    // console.log("items: ", items);
    call("/todo", "POST", item)
      .then((response) => setItems(response.data))
    // .then(console.log(items))
  }

  let todoItems = items.length > 0 && (
    <Paper style={{ margin: 16 }}>
      <List>
        {items.map(item => (
          <Todo item={item} key={item.id} editItem={editItem} deleteItem={deleteItem} />
        ))}
      </List>
    </Paper>
  )

  //navigationBar 추가
  let navigationBar = (
    <AppBar position='static'>
      <Toolbar>
        <Grid justifyContent="space-between" container>
          <Grid item>
            <Typography variant="h6">Todo</Typography>
          </Grid>
          <Grid item>
            <Button color="inherit" raised onClick={signout}>
              로그아웃
            </Button>
          </Grid>
        </Grid>
      </Toolbar>
    </AppBar>
  );


  /*로딩 중이 아닐때 렌더링할 부분*/
  let todoListPage = (
    <div>
      {navigationBar}
      <Container maxWidth="md">
        <AddTodo addItem={addItem}/>
        <div className='TodoList'>{todoItems}</div>
      </Container>
    </div>
  );

    // 로딩중일 때 렌더링 할 부분
    let loadingPage = <h1>로딩 중..</h1>
    let content = loadingPage;

    if(!loading){
      /* 로딩중이 아니라면 todoListPage를 선택 */
      content = todoListPage;
    }

    /* 선택한 content 렌더링 */
    return <div className="App">{content}</div>;
  }


  // return (
  //   <div className="App">
  //     {navigationBar} {/*네비게이션바 렌더링 */}
  //     <Container maxWidth="md">
  //       <AddTodo addItem={addItem} />
  //       <div className='TodoList'>{todoItems}</div>
  //     </Container>
  //   </div>
  // );


export default App
