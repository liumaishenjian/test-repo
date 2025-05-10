document.addEventListener('DOMContentLoaded', () => {
    const sqlInput = document.getElementById('sqlInput');
    const executeBtn = document.getElementById('executeBtn');
    const toggleInputMode = document.getElementById('toggleInputMode');
    const errorMessage = document.querySelector('.error-message');
    const loading = document.querySelector('.loading');
    const resultTable = document.getElementById('resultTable');
    
    let isNaturalLanguageMode = true;
    
    toggleInputMode.addEventListener('click', () => {
        isNaturalLanguageMode = !isNaturalLanguageMode;
        toggleInputMode.textContent = isNaturalLanguageMode ? '切换到SQL模式' : '切换到自然语言模式';
        sqlInput.placeholder = isNaturalLanguageMode ? '请输入自然语言查询...' : '请输入SQL查询语句...';
    });
    
    executeBtn.addEventListener('click', async () => {
        const query = sqlInput.value.trim();
        if (!query) {
            showError('请输入查询语句');
            return;
        }
        
        try {
            showLoading(true);
            hideError();
            
            const response = await fetch('/api/sql/query', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(query)
            });
            
            if (!response.ok) {
                throw new Error('查询执行失败');
            }
            
            const data = await response.json();
            if (data.code === 200) {
                displayResults(data.data);
            } else {
                showError(data.message || '查询执行失败');
            }
        } catch (error) {
            showError(error.message);
        } finally {
            showLoading(false);
        }
    });
    
    function showError(message) {
        errorMessage.textContent = message;
        errorMessage.style.display = 'block';
    }
    
    function hideError() {
        errorMessage.style.display = 'none';
    }
    
    function showLoading(show) {
        loading.style.display = show ? 'block' : 'none';
        executeBtn.disabled = show;
    }
    
    function displayResults(data) {
        if (!Array.isArray(data) || data.length === 0) {
            resultTable.innerHTML = '<tr><td>无查询结果</td></tr>';
            return;
        }
        
        const headers = Object.keys(data[0]);
        const thead = `<tr>${headers.map(h => `<th>${h}</th>`).join('')}</tr>`;
        const tbody = data.map(row => `
            <tr>
                ${headers.map(h => `<td>${row[h] ?? ''}</td>`).join('')}
            </tr>
        `).join('');
        
        resultTable.innerHTML = `
            <thead>${thead}</thead>
            <tbody>${tbody}</tbody>
        `;
    }
});