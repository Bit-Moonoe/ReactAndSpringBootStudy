import { RepeatOneSharp } from '@mui/icons-material';
import { Button, Container, Grid, TextField, Typography } from '@mui/material';
import React from 'react'
import { Link } from 'react-router-dom';
import { signup } from './service/ApiService';

function SignUp() {
    const handleSubmit = (e) => {
        e.preventDefault();
        //오브젝트에서 form에 저장된 데이터를 앱의 형태로 바꿔줌.
        const data = new FormData(e.target);
        const username = data.get("username");
        const password = data.get("password");
        signup({ username: username, password: password })
            .then((response) => {
                if(response.id===null){
                    alert("이미 가입된 회원입니다.");
                    // window.location.href="/signin";
                }else{
                    alert("회원 가입되었습니다. 로그인 후 진행해주세요.");
                    window.location.href="/login";
                }
            })
            
            
    };


return (
    <Container component="main" maxWidth="xs" style={{ marginTop: "8%" }}>
        <form noValidate onSubmit={handleSubmit}>
            <Grid container spacing={2}>
                <Grid item xs={12}>
                    <Typography component="h1" variant='h5'>
                        계정 생성
                    </Typography>
                </Grid>
                <Grid item xs={12}>
                    <TextField
                        autoComplete='fname'
                        name="username"
                        variant='outlined'
                        required
                        fullWidth
                        id="username"
                        label="아이디"
                        autoFocus
                    />
                </Grid>
                <Grid item xs={12}>
                    <TextField
                        variant='outlined'
                        required
                        fullWidth
                        name="password"
                        label="패스워드"
                        type="password"
                        id="password"
                        autoComplete='current-password'
                    />
                </Grid>
                <Grid item xs={12}>
                    <Button
                        type="submit"
                        fullWidth
                        variant='contained'
                        color="primary"
                    >
                        계정 생성
                    </Button>
                </Grid>
            </Grid>
            <Grid container justify = "flex-end">
                <Grid item>
                    <Link to = "/login" variant="body2">
                        이미 계정이 있습니까? 로그인 하세요.
                    </Link>
                </Grid>
            </Grid>
        </form>
    </Container>
)
};
export default SignUp;