function deleteComment(commentId){

    $.ajax({
        type: "POST",
        data: {"commentId" : commentId},
        url: "/comment/" + commentId + "/delete",
        success:function(data){
            if(data == 1){
                console.log("commentId = " + commentId);
            }
        }

    })
}