package com.example.bpr2server.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.bpr2server.mapper.ExamQuestionAnswersMapper;
import com.example.bpr2server.mapper.ExamQuestionsMapper;
import com.example.bpr2server.model.ExamQuestionAnswers;
import com.example.bpr2server.model.ExamQuestions;
import com.example.bpr2server.service.ExamQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("ExamQuestionsService")
public class ExamQuestionsServiceImpl implements ExamQuestionsService {
    @Autowired
    ExamQuestionsMapper examQuestionsMapper;
    @Autowired
    ExamQuestionAnswersMapper examQuestionAnswersMapper;

    @Override
    public List<ExamQuestions> fetchExamQuestions(int examId) {
        List<ExamQuestions> examQuestionsList = examQuestionsMapper.getExamQuestions(examId);

        for (ExamQuestions question: examQuestionsList){
            int questionId = question.getQuestionId();
            List<ExamQuestionAnswers> answers = examQuestionAnswersMapper.selectByExamIdAndQuestionId(examId, questionId);
            question.setExamQuestionAnswers(answers);
        }

        return examQuestionsList;
    }

    @Override
    public String updateExamQuestions(int examId, List<ExamQuestions> newQuestionsList) {
        //delete
        List<ExamQuestions> originalQuestionList = examQuestionsMapper.getExamQuestions(examId);
        for (ExamQuestions item: originalQuestionList){
            QueryWrapper<ExamQuestionAnswers> examQuestionAnswersQueryWrapper = new QueryWrapper();
            examQuestionAnswersQueryWrapper.eq("exam_id", examId);
            examQuestionAnswersQueryWrapper.eq("question_id",item.getQuestionId());
            examQuestionAnswersMapper.delete(examQuestionAnswersQueryWrapper);
        }

        QueryWrapper<ExamQuestions> examQuestionsQueryWrapper = new QueryWrapper();
        examQuestionsQueryWrapper.eq("exam_id", examId);
        examQuestionsMapper.delete(examQuestionsQueryWrapper);

        //add
        for (ExamQuestions question: newQuestionsList){
            examQuestionsMapper.insert(question);
            for (ExamQuestionAnswers answer: question.getExamQuestionAnswers()){
                examQuestionAnswersMapper.insert(answer);
            }
        }
        return "Update success";

//        int questionUpdate = 0;
//        int answerUpdate = 0;
//        //QuestionsUpdate
//        List<ExamQuestions> originalQuestionList = examQuestionsMapper.getExamQuestions(examId);
//
//        List<ExamQuestions> toAddQuestions = new ArrayList<>();
//        for (ExamQuestions item: newQuestionsList){
//            if (!originalQuestionList.contains(item)){
//                toAddQuestions.add(item);
//            }
//        }
//
//        List<ExamQuestions> toDeleteQuestions = new ArrayList<>();
//        for (ExamQuestions item: originalQuestionList){
//            if (!newQuestionsList.contains(item)){
//                toDeleteQuestions.add(item);
//            }
//        }
//
//        for (ExamQuestions item: toAddQuestions){
//            examQuestionsMapper.insert(item);
//            questionUpdate++;
//        }
//
//        for (ExamQuestions item: toDeleteQuestions){
//            QueryWrapper<ExamQuestions> toDelExamQuestionsQueryWrapper = new QueryWrapper();
//            toDelExamQuestionsQueryWrapper.eq("exam_questions_id", item.getExamQuestionsId());
//            examQuestionsMapper.delete(toDelExamQuestionsQueryWrapper);
//            questionUpdate++;
//        }

        //AnswersUpdate

        //List<ExamQuestionAnswers> originalAnswerList = examQuestionAnswersMapper.selectByQuestionId()
//        if (questionUpdate > 0 || answerUpdate > 0){
//            return "Update success";
//        }
//        else return "Update failure";
    }
}
