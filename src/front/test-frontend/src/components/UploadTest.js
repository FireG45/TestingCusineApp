import * as React from 'react';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { useNavigate } from 'react-router-dom';
import List from '@mui/material/List';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import Collapse from '@mui/material/Collapse';
import StarBorder from '@mui/icons-material/StarBorder';
import { Checkbox, Fab, IconButton, ListItem } from '@mui/material';
import AddIcon from '@mui/icons-material/Add';
import DeleteIcon from '@mui/icons-material/Delete';

export default function UploadTest() {
    const [file, setFile] = React.useState(null);
    const [title, setTitle] = React.useState(null);
    const [author, setAuthor] = React.useState(null);
    const navigate = useNavigate();

    const [questionsList, setQuestionsList] = React.useState(['']);
    const [questionsImgList, setQuestionsImgList] = React.useState(['']);
    const [answersList, setAnswersList] = React.useState([['']]);
    const [answersBools, setAnswersBools] = React.useState([[false]]);

    const addQuestion = (vl) => {
        setQuestionsList(questionsList.concat([vl]));
        setQuestionsImgList(questionsImgList.concat([vl]));
        setAnswersList(answersList.concat([['']]))
        setAnswersBools(answersBools.concat([[false]]))
    };

    const deleteQuestion = (index) => {
        let temp = questionsList.filter((item, i) => i !== index);
        let temp1 = questionsImgList.filter((item, i) => i !== index);
        let temp2 = answersList.filter((item, i) => i !== index)
        let temp3 = answersBools.filter((item, i) => i !== index)
        setQuestionsList(temp);
        setQuestionsImgList(temp1);
        setAnswersList(temp2)
        setAnswersBools(temp3)
    };

    const addAnswer = (i) => {
        answersList[i].push([''])
        setAnswersList(answersList.filter(() => true));
        answersBools[i].push([false])
        setAnswersBools(answersBools.filter(() => true));
    };

    const deleteAnswer = (index, jndex) => {
        answersList[index] = answersList[index].filter((item, j) => j !== jndex);
        setAnswersList(answersList.filter(() => true));
        answersBools[index] = answersBools[index].filter((item, j) => j !== jndex);
        setAnswersBools(answersBools.filter(() => true));
    };

    const handleFileChange = (e) => {
        if (e.target.value) {
            setFile(e.target.value);
        }
    };

    const handleTitleChange = (e) => {
        if (e.target.value) {
            setTitle(e.target.value);
        }
    }

    const handleCheckBox = (i, j) => {
        answersBools[i][j] = !answersBools[i][j]
        setAnswersBools(answersBools.filter(() => true))
    }

    const handleAuthorChange = (e) => {
        if (e.target.value) {
            setAuthor(e.target.value);
        }
    }

    const handleUpload = async (e) => {
        e.preventDefault()
        console.log("Uploading file...");

        const formData = new FormData();
        formData.append("name", author);
        formData.append("description", title);
        formData.append("imgUrl", file);
        formData.append("authorId", 1);
        formData.append("questions", questionsList);
        formData.append("questionsImgs", questionsImgList);
        formData.append("answers", answersList);
        formData.append("answersChecks", answersBools);

        console.log(answersList)
        console.log(answersBools)

        try {
            const result = await fetch("http://localhost:8080/upload", {
                method: "POST",
                body: formData,
            });

            const data = await result.json();

            if (result.ok) {
                navigate('/tests/' + data.id)
            }

        } catch (error) {
            console.error(error);
        }
    };

    return (
        <Container component="main" maxWidth="lg">
            <CssBaseline />
            <Box
                sx={{
                    marginTop: 8,
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'center',
                }}
            >
                <Typography component="h1" variant="h5">
                    Создать тест!
                </Typography>
                <Box component="form" onSubmit={handleUpload} sx={{ mt: 3 }}>
                    <Grid container spacing={2}>
                        <Grid item xs={12}>
                            <TextField
                                onChange={handleAuthorChange}
                                required
                                fullWidth
                                id="author"
                                label="Название теста"
                                name="author"
                                autoComplete="author"
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField
                                onChange={handleTitleChange}
                                required
                                fullWidth
                                name="title"
                                label="Описание"
                                type="title"
                                id="title"
                                autoComplete="title"
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField
                                onChange={handleFileChange}
                                required
                                fullWidth
                                name="file"
                                label="Ссылка на изображение"
                                id="file"
                            />
                        </Grid>
                    </Grid>
                    <br />
                    {/* Вопрпосы */}
                    <Typography component="h1" variant="h5">
                        Вопросы
                    </Typography>

                    <List
                        sx={{ width: '100%', bgcolor: 'background.paper' }}
                        component="nav"
                        aria-labelledby="nested-list-subheader"

                    >
                        {questionsList.length > 0 && questionsList.map((item, i) => {
                            return (<>
                                <ListItem
                                    key={i}
                                    secondaryAction={
                                        <IconButton edge="end" aria-label="add" onClick={() => addAnswer(i)}>
                                            <AddIcon />
                                        </IconButton>
                                    }>
                                    <ListItemIcon>
                                        <h1>{i + 1}</h1>
                                        <IconButton edge="end" aria-label="delete" onClick={() => deleteQuestion(i)}>
                                            <DeleteIcon />
                                        </IconButton>
                                    </ListItemIcon>
                                    <TextField
                                        required
                                        fullWidth
                                        name={"q" + i}
                                        label="Вопрос"
                                        id={"q" + i}
                                        onChange={(e) => {
                                            questionsList[i] = e.target.value;
                                            setQuestionsList(questionsList.filter(() => true))
                                        }}
                                    />
                                    <TextField
                                        required
                                        fullWidth
                                        name={"q" + i}
                                        label="Ссылка на картинку"
                                        id={"qi" + i}
                                        onChange={(e) => {
                                            questionsImgList[i] = e.target.value;
                                            setQuestionsImgList(questionsImgList.filter(() => true))
                                        }}
                                    />
                                </ListItem>
                                <Collapse in={true} timeout="auto">
                                    <List component="div" disablePadding>
                                        {answersList.length > 0 && answersList[i].length > 0 && answersList[i].map((item, j) => {
                                            return (
                                                <ListItem sx={{ pl: 4 }}
                                                    key={i + j}
                                                    secondaryAction={
                                                        <IconButton edge="end" aria-label="delete" onClick={() => deleteAnswer(i, j)}>
                                                            <DeleteIcon />
                                                        </IconButton>
                                                    }
                                                >
                                                    <ListItemIcon>
                                                        <h1>{j + 1}</h1>
                                                        <Checkbox checked={answersBools[i][j]} onChange={() => handleCheckBox(i, j)}/>
                                                    </ListItemIcon>
                                                    <TextField
                                                        required
                                                        fullWidth
                                                        name={"a" + i + "_" + j}
                                                        label="Ответ"
                                                        id={"a" + i + "_" + j}
                                                        onChange={(e) => {
                                                            answersList[i][j] = e.target.value;
                                                            setAnswersList(answersList.filter(() => true))
                                                        }}
                                                    />
                                                </ListItem>
                                            )
                                        })}
                                    </List>
                                </Collapse>
                            </>)
                        })}
                    </List>
                    <Button
                        fullWidth
                        variant="contained"
                        sx={{ mt: 3, mb: 2 }}
                        onClick={() => addQuestion('')}
                    >
                        <AddIcon />
                    </Button>
                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        sx={{ mt: 3, mb: 2 }}
                        onClick={handleUpload}
                    >
                        Создать
                    </Button>
                </Box>

            </Box>
        </Container>
    );
}
