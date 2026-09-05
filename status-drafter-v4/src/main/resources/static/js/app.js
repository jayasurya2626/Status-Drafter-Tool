function confirmDelete(){return confirm('Are you sure you want to delete this record?');}
document.addEventListener('DOMContentLoaded',()=>{document.querySelectorAll('input[type=date]').forEach(i=>{if(i.name==='startDate'&&i.form){i.addEventListener('change',()=>{const d=i.form.querySelector('[name=deadline]');if(d&&!d.value)d.value=i.value})}})});
