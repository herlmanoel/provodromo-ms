import React, { useState } from 'react';
import Button from "./Button.jsx";
import { FaRegTrashAlt} from "react-icons/fa";
const Questionnaire = () => {
    const [isAccordionOpen, setIsAccordionOpen] = useState(false);
    const [question, setQuestion] = useState('');
    const [options, setOptions] = useState(['', '', '', '', '']);
    const [questionsList, setQuestionsList] = useState([]);
    const [activeAccordionIndex, setActiveAccordionIndex] = useState(null);

    const handleAccordionToggle = () => {
        setIsAccordionOpen(!isAccordionOpen);
        setActiveAccordionIndex(null);
    };

    const handleQuestionChange = (e) => {
        setQuestion(e.target.value);
    };

    const handleOptionChange = (index, value) => {
        const newOptions = [...options];
        newOptions[index] = value;
        setOptions(newOptions);
    };

    const handleRemoveOption = (index) => {
        const newOptions = options.filter((_, i) => i !== index);
        setOptions(newOptions);
    };

    const handleSaveQuestion = () => {
        if (question.trim() && options.some(option => option.trim())) {
            setQuestionsList([...questionsList, { question, options }]);
            setQuestion('');
            setOptions(['', '', '', '', '']);
            setIsAccordionOpen(false);
        } else {
            alert("Preencha a questão e pelo menos uma opção.");
        }
    };

    const handleAccordionClick = (index) => {
        setActiveAccordionIndex(index === activeAccordionIndex ? null : index);
    };

    const handleRemoveQuestion = (index) => {
        const newQuestionsList = questionsList.filter((_, i) => i !== index);
        setQuestionsList(newQuestionsList);
    };

    return (
        <div className="p-4 w-max">
            <Button titulo={"Adicionar Questão"} handleClick={handleAccordionToggle}/>

            {isAccordionOpen && (
                <div className=" border rounded-md border-gray-700 text-gray-500 p-4 shadow-md mb-4">
                    <input
                        type="text"
                        placeholder="Digite a questão"
                        value={question}
                        onChange={handleQuestionChange}
                        className="bg-transparent border border-gray-300 p-2 mb-4 w-full rounded-md"
                    />
                    {options.map((option, index) => (
                        <div key={index} className="flex items-center mb-2">
                            <input
                                type="text"
                                placeholder={`Opção ${index + 1}`}
                                value={option}
                                onChange={(e) => handleOptionChange(index, e.target.value)}
                                className="bg-transparent border border-gray-300 p-2 flex-1 mr-2 rounded-md"
                            />
                            <button
                                className=" bg-transparent px-2 py-1 rounded-md hover:bg-red-600"
                                onClick={() => handleRemoveOption(index)}
                            >
                                <FaRegTrashAlt />
                            </button>
                        </div>
                    ))}
                    <button
                        className="bg-green-500 text-white px-4 py-2 rounded-md mt-4 block w-full hover:bg-green-600"
                        onClick={handleSaveQuestion}
                    >
                        Salvar Questão
                    </button>
                </div>
            )}
            <div>
                {questionsList.map((item, index) => (
                    <div key={index} className="border rounded-md border-gray-700 text-gray-500 mb-4">
                        <div
                            className="border-b border-gray-700 p-4 cursor-pointer"
                            onClick={() => handleAccordionClick(index)}
                        >
                            <strong>{item.question}</strong>
                            <button
                                className="bg-transparent px-2 py-1 rounded-md hover:bg-red-600"
                                onClick={(e) => {
                                    e.stopPropagation();
                                    handleRemoveQuestion(index);
                                }}
                            >
                                <FaRegTrashAlt/>
                            </button>
                        </div>
                        {activeAccordionIndex === index && (
                            <div className="bg-gray-100 p-4 rounded-md shadow-md mt-2">
                                <ul className="list-inside list-disc">
                                    {item.options.map((option, i) => (
                                        <li key={i}>{option}</li>

                                    ))}
                                </ul>
                            </div>
                        )}
                    </div>
                ))}
            </div>
        </div>
    );
};

export default Questionnaire;
